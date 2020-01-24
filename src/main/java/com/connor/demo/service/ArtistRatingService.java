package com.connor.demo.service;

import com.connor.demo.model.Artist;
import com.connor.demo.model.ArtistRating;
import com.connor.demo.model.ArtistRatingKey;
import com.connor.demo.model.User;
import com.connor.demo.repository.ArtistRatingRepository;
import com.connor.demo.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;

@Service
public class ArtistRatingService {

    private ArtistRatingRepository artistRatingRepository;
    private ArtistService artistService;
    private UserService userService;
    private ArtistRepository artistRepository;


    @Autowired
    public ArtistRatingService(ArtistRatingRepository artistRatingRepository, UserService userService,
                               ArtistService artistService, ArtistRepository artistRepository) {
        this.artistRatingRepository = artistRatingRepository;
        this.userService = userService;
        this.artistService = artistService;
        this.artistRepository = artistRepository;
    }

    public Iterable<ArtistRating> findAll(){return artistRatingRepository.findAll();}


    public ArtistRating addRating(HttpServletRequest request, Artist artist, int rating){
        User user = userService.getUserByToken(request);

        Artist artist1 = artistService.findById(artist.getId());
        if(artist1 == null){
            artist1 = artistService.addArtist(request, artist);
        }

        ArtistRatingKey artistRatingKey = new ArtistRatingKey(user.getUserProfile().getProfileId(), artist1.getId());

        ArtistRating artistRating = new ArtistRating(user.getUserProfile(), artist1, rating);
        artistRating.setId(artistRatingKey);
//        artist1.getRatings().add(artistRating);
//        artistRepository.save(artist1);
//
//        artistRating.
//
//
//        artist1.getUserProfile().add(user.getUserProfile());
//        artistRepository.save(artist);
//        user.getUserProfile().getArtists().add(artist);
//        userRepository.save(user);

        return artistRatingRepository.save(artistRating);
//
//        return null;
    }

}
