package com.connor.demo.service;

import com.connor.demo.model.UserProfile;
import com.connor.demo.model.artist.Artist;
import com.connor.demo.model.artist.ArtistRating;
import com.connor.demo.model.artist.ArtistRatingKey;
import com.connor.demo.model.User;
import com.connor.demo.repository.ArtistRatingRepository;
import com.connor.demo.repository.ArtistRepository;
import com.connor.demo.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;


@Service
public class ArtistRatingService {

    private ArtistRatingRepository artistRatingRepository;
    private ArtistService artistService;
    private UserService userService;
    private ArtistRepository artistRepository;
    private UserProfileRepository userProfileRepository;



    @Autowired
    public ArtistRatingService(ArtistRatingRepository artistRatingRepository, UserService userService,
                               ArtistService artistService, ArtistRepository artistRepository,
                               UserProfileRepository userProfileRepository) {
        this.artistRatingRepository = artistRatingRepository;
        this.userService = userService;
        this.artistService = artistService;
        this.artistRepository = artistRepository;
        this.userProfileRepository = userProfileRepository;
    }

    public Iterable<ArtistRating> findAll(){return artistRatingRepository.findAll();}

    public Iterable<ArtistRating> findRecent(){
        Pageable paging = PageRequest.of(0, 5, Sort.by("modifiedDate").descending());
        return artistRatingRepository.findAll(paging);
    }


    public Iterable<ArtistRating> getUserArtistRatings(Long id, Integer pageNo, Integer pageSize, String sortBy)
    {
        Pageable paging;
        if (sortBy.equalsIgnoreCase("name")){
            paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        } else {
            paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
        }

        Page<ArtistRating> pagedResult = artistRatingRepository.findByUserProfileProfileId(id, paging);

        if(pagedResult.hasContent()) {
            System.out.println(pagedResult.getTotalElements());
            return pagedResult;
        } else {
            return null;
        }
    }

    public ArtistRating findUserArtistRating(HttpServletRequest request, Long id){
        User user = userService.getUserByToken(request);
        Artist artist = artistRepository.findById(id).orElse(null);
        if (artist != null) {
            return artistRatingRepository.findByArtistAndUserProfileProfileId(artist, user.getUserProfile().getProfileId());
        }
        return new ArtistRating(null, null, new Artist(), 0);
    }



    public ArtistRating addRating(HttpServletRequest request, Artist artist, int rating){
        User user = userService.getUserByToken(request);

        Artist artist1 = artistService.findById(artist.getId());
        if(artist1 == null){
            artist1 = artistService.addArtist(request, artist);
        }

        ArtistRatingKey artistRatingKey = new ArtistRatingKey(user.getUserProfile().getProfileId(), artist1.getId());

        ArtistRating artistRating = new ArtistRating(artistRatingKey, user.getUserProfile(), artist1, rating);

        return artistRatingRepository.save(artistRating);
    }

}
