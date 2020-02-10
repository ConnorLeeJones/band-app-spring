package com.connor.demo.service;

import com.connor.demo.model.User;
import com.connor.demo.model.album.AlbumRating;
import com.connor.demo.model.movie.Movie;
import com.connor.demo.model.movie.MovieRating;
import com.connor.demo.model.movie.MovieRatingKey;
import com.connor.demo.repository.MovieRatingRepository;
import com.connor.demo.repository.MovieRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class MovieRatingService {

    private MovieRatingRepository movieRatingRepository;
    private MovieRepository movieRepository;
    private MovieService movieService;
    private UserService userService;
    private FriendService friendService;


    public MovieRatingService(MovieRatingRepository movieRatingRepository, MovieRepository movieRepository, MovieService movieService, UserService userService, FriendService friendService) {
        this.movieRatingRepository = movieRatingRepository;
        this.movieRepository = movieRepository;
        this.movieService = movieService;
        this.userService = userService;
        this.friendService = friendService;
    }

    public Iterable<MovieRating> findAll(){return movieRatingRepository.findAll();}

    public Iterable<MovieRating> getUserMovieRatings(Long id, Integer pageNo, Integer pageSize, String sortBy)
    {
        for (MovieRating rating : movieRatingRepository.findAll()){
            rating.setName(rating.getMovie().getTitle());
            movieRatingRepository.save(rating);
        }

        Pageable paging;
        if (sortBy.equalsIgnoreCase("name")){
            paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        } else {
            paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
        }

        Page<MovieRating> pagedResult = movieRatingRepository.findByUserProfileProfileId(id, paging);

        if(pagedResult.hasContent()) {
            System.out.println(pagedResult.getTotalElements());
            return pagedResult;
        } else {
            return null;
        }
    }

    public Iterable<MovieRating> findRecentFriendRatings(HttpServletRequest request, Integer pageNo, Integer pageSize){
        List<Long> ids = (List<Long>) friendService.findFriendIds(request);
        System.out.println(ids);
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("modified_date").descending());
        return movieRatingRepository.findByUserProfileFriends(ids, paging);
    }


    public MovieRating findUserMovieRating(HttpServletRequest request, String id){
        User user = userService.getUserByToken(request);
        Movie movie = movieRepository.findById(id).orElse(null);
        if (movie != null) {
            return movieRatingRepository.findByMovieAndUserProfileProfileId(movie, user.getUserProfile().getProfileId());
        }
        return new MovieRating(null, null, new Movie(), 0);
    }

    public MovieRating addRating(HttpServletRequest request, Movie movie, int rating){
        User user = userService.getUserByToken(request);

        Movie movie1 = movieService.findById(movie.getId());
        if(movie1 == null){
            movie1 = movieService.addMovie(movie);
        }

        MovieRatingKey movieRatingKey = new MovieRatingKey(user.getUserProfile().getProfileId(), movie1.getId());

        MovieRating movieRating = new MovieRating(movieRatingKey, user.getUserProfile(), movie1, rating);

        return movieRatingRepository.save(movieRating);
    }

}
