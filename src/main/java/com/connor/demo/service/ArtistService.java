package com.connor.demo.service;

import com.connor.demo.model.Artist;
import com.connor.demo.model.User;
import com.connor.demo.repository.ArtistRepository;
import com.connor.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;

@Service
public class ArtistService {

    private ArtistRepository artistRepository;
    private UserRepository userRepository;
    private UserService userService;


    @Autowired
    public ArtistService(ArtistRepository artistRepository, UserRepository userRepository, UserService userService) {
        this.artistRepository = artistRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public Iterable<Artist> findAll(){return artistRepository.findAll();}

    public Artist findById(Long id){return artistRepository.findById(id).orElse(null);}

    public Artist unfollowArtist(HttpServletRequest request, Long id) {
        User user = userService.getUserByToken(request);
        Artist artist = artistRepository.findById(id).orElse(null);
        if (artist != null && ((HashSet<Long>) this.userService.getArtistsIdsByProfileId(user.getId())).contains(artist.getId())) {
            artist.getUserProfile().remove(user.getUserProfile());
            artistRepository.save(artist);
            user.getUserProfile().getArtists().remove(artist);
            userRepository.save(user);

            return artistRepository.save(artist);
        } else {
            return null;
        }
    }


    public Artist addArtist(HttpServletRequest request, Artist artist) {
        User user = userService.getUserByToken(request);
        if (!((HashSet<Long>) this.userService.getArtistsIdsByProfileId(user.getId())).contains(artist.getId())) {
            artist.getUserProfile().add(user.getUserProfile());
            artistRepository.save(artist);
            user.getUserProfile().getArtists().add(artist);
            userRepository.save(user);

            return artistRepository.save(artist);
        } else {
          return null;
        }
    }

}
