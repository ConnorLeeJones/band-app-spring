package com.connor.demo.service;

import com.connor.demo.model.album.AlbumRating;
import com.connor.demo.model.artist.Artist;
import com.connor.demo.model.artist.ArtistRating;
import com.connor.demo.model.User;
import com.connor.demo.model.UserProfile;
import com.connor.demo.repository.UserRepository;
import com.connor.demo.security.CustomException;
import com.connor.demo.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;


@Service
public class UserService {

    private UserRepository userRepository;
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
    private PasswordEncoder passwordEncoder;


    @Autowired
    public UserService(UserRepository userRepository, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }


    public User findUserById(Long id){
        return userRepository.findUserById(id);
    }

    public Iterable<User> findAll(){return userRepository.findAll();}

    public User logIn(User user) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            user = userRepository.findByUsername(user.getUsername());
            user.setToken(jwtTokenProvider.createToken(user.getUsername()));
            return user;
        } catch (AuthenticationException e) {
            System.out.println(e.getMessage());
            throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public User signUp(User user) {
        if (!userRepository.existsByUsername(user.getUsername())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            UserProfile profile = new UserProfile(user.getId());
            user.setUserProfile(profile);
            user.setToken(jwtTokenProvider.createToken(user.getUsername()));
            return userRepository.save(user);
        } else {
            throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

//    public Iterable<User> test(){
//        return userRepository.findAllUserIdAndUsername();
//    }

    public Iterable<ArtistRating> getUserRatings(Long id){
        User user = userRepository.findUserById(id);
        return user.getUserProfile().getRatings();
    }

    public Iterable<AlbumRating> getUserAlbumRatings(Long id){
        User user = userRepository.findUserById(id);
        return user.getUserProfile().getAlbum_ratings();
    }


    public Iterable<Artist> getArtistsByProfileId(Long id){
        User user = userRepository.findUserById(id);
        return user.getUserProfile().getArtists();
    }

    public Iterable<Long> getArtistsIdsByProfileId(Long id){
        User user = userRepository.findUserById(id);
        Set<Artist> artists = user.getUserProfile().getArtists();
        Set<Long> ids = new HashSet<>();
        for (Artist artist : artists){
            ids.add(artist.getId());
        }
        return ids;
    }


    public User getUserByToken(HttpServletRequest req) {
        return userRepository.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
    }

}