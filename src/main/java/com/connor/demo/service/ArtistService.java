package com.connor.demo.service;

import com.connor.demo.model.Artist;
import com.connor.demo.model.User;
import com.connor.demo.repository.ArtistRepository;
import com.connor.demo.repository.UserRepository;
import com.connor.demo.security.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ArtistService {

    private ArtistRepository artistRepository;
    private UserRepository userRepository;


    @Autowired
    public ArtistService(ArtistRepository artistRepository, UserRepository userRepository) {
        this.artistRepository = artistRepository;
        this.userRepository = userRepository;
    }

    public Iterable<Artist> findAll(){return artistRepository.findAll();}


    public Artist addArtist(Artist artist) {
        if (!artistRepository.existsById(artist.getId())) {
            User user = userRepository.findUserById(1L);




            artist.getUserProfile().add(user.getUserProfile());
            artistRepository.save(artist);
            user.getUserProfile().getArtists().add(artist);
            userRepository.save(user);
//            artistRepository.save(artist);


            return artistRepository.save(artist);
        } else {
            throw new CustomException("Artist already exists", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

}
