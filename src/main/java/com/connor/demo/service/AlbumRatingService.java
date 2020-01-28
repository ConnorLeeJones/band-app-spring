package com.connor.demo.service;

import com.connor.demo.model.User;
import com.connor.demo.model.album.Album;
import com.connor.demo.model.album.AlbumRating;
import com.connor.demo.model.album.AlbumRatingKey;

import com.connor.demo.repository.AlbumRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@Service
public class AlbumRatingService {

    private AlbumRatingRepository albumRatingRepository;
    private AlbumService albumService;
    private UserService userService;



    @Autowired
    public AlbumRatingService(AlbumRatingRepository albumRatingRepository, UserService userService,
                              AlbumService albumService) {
        this.albumRatingRepository = albumRatingRepository;
        this.userService = userService;
        this.albumService = albumService;
    }

    public Iterable<AlbumRating> findAll(){return albumRatingRepository.findAll();}


    public AlbumRating findUserAlbumRating(HttpServletRequest request, Long id){
        Set<AlbumRating> ratings = userService.getUserByToken(request).getUserProfile().getAlbum_ratings();
        for (AlbumRating rating : ratings){
            if (rating.getAlbum().getAlbum_id().equals(id)){
                return rating;
            }
        }
        return new AlbumRating(null, null, 0);
    }

    public AlbumRating addRating(HttpServletRequest request, Album album, int rating){
        User user = userService.getUserByToken(request);

        Album album1 = albumService.findById(album.getAlbum_id());
        if(album1 == null){
            album1 = albumService.addAlbum(request, album);
        }

        AlbumRatingKey albumRatingKey = new AlbumRatingKey(user.getUserProfile().getProfileId(), album1.getAlbum_id());

        AlbumRating albumRating = new AlbumRating(albumRatingKey, user.getUserProfile(), album1, rating);

        return albumRatingRepository.save(albumRating);
    }

}
