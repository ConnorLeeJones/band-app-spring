package com.connor.demo.service;

import com.connor.demo.model.User;
import com.connor.demo.model.album.Album;
import com.connor.demo.model.artist.Artist;
import com.connor.demo.repository.AlbumRepository;
import com.connor.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;

@Service
public class AlbumService {

    private AlbumRepository albumRepository;
    private UserRepository userRepository;
    private UserService userService;


    @Autowired
    public AlbumService(AlbumRepository albumRepository, UserRepository userRepository, UserService userService) {
        this.albumRepository = albumRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public Iterable<Album> findAll(){return albumRepository.findAll();}

    public Album findById(Long id){return albumRepository.findById(id).orElse(null);}

    public Album addAlbum(HttpServletRequest request, Album album) {
        User user = userService.getUserByToken(request);
        if (!user.getUserProfile().getAlbums().contains(album)) {
            album.getUserProfiles().add(user.getUserProfile());
            albumRepository.save(album);
            user.getUserProfile().getAlbums().add(album);
            userRepository.save(user);

            return albumRepository.save(album);
        } else {
            return null;
        }
    }


}
