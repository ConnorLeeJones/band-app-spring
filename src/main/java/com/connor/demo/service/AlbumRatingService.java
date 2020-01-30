package com.connor.demo.service;

import com.connor.demo.model.User;
import com.connor.demo.model.album.Album;
import com.connor.demo.model.album.AlbumRating;
import com.connor.demo.model.album.AlbumRatingKey;

import com.connor.demo.model.artist.Artist;
import com.connor.demo.model.artist.ArtistRating;
import com.connor.demo.repository.AlbumRatingRepository;
import com.connor.demo.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@Service
public class AlbumRatingService {

    private AlbumRatingRepository albumRatingRepository;
    private AlbumRepository albumRepository;
    private AlbumService albumService;
    private UserService userService;



    @Autowired
    public AlbumRatingService(AlbumRatingRepository albumRatingRepository, UserService userService,
                              AlbumService albumService, AlbumRepository albumRepository) {
        this.albumRatingRepository = albumRatingRepository;
        this.userService = userService;
        this.albumService = albumService;
        this.albumRepository = albumRepository;
    }

    public Iterable<AlbumRating> findAll(){return albumRatingRepository.findAll();}

    public Iterable<AlbumRating> getUserAlbumRatings(Long id, Integer pageNo, Integer pageSize, String sortBy)
    {
        for (AlbumRating rating : albumRatingRepository.findAll()){
            rating.setName(rating.getAlbum().getTitle());
            albumRatingRepository.save(rating);
        }


        Pageable paging;
        if (sortBy.equalsIgnoreCase("name")){
            paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        } else {
            paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
        }

        Page<AlbumRating> pagedResult = albumRatingRepository.findByUserProfileProfileId(id, paging);

        if(pagedResult.hasContent()) {
            System.out.println(pagedResult.getTotalElements());
            return pagedResult;
        } else {
            return null;
        }
    }



//    public AlbumRating findUserAlbumRating(HttpServletRequest request, Long id){
//        Set<AlbumRating> ratings = userService.getUserByToken(request).getUserProfile().getAlbum_ratings();
//        for (AlbumRating rating : ratings){
//            if (rating.getAlbum().getAlbum_id().equals(id)){
//                return rating;
//            }
//        }
//        return new AlbumRating(null, null, null, 0);
//    }

    public AlbumRating findUserAlbumRating(HttpServletRequest request, Long id){
        User user = userService.getUserByToken(request);
        Album album = albumRepository.findById(id).orElse(null);
        if (album != null) {
            return albumRatingRepository.findByAlbumAndUserProfileProfileId(album, user.getUserProfile().getProfileId());
        }
        return new AlbumRating(null, null, new Album(), 0);
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
