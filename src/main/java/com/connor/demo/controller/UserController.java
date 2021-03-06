package com.connor.demo.controller;


import com.connor.demo.model.UserDto;
import com.connor.demo.model.album.AlbumRating;
import com.connor.demo.model.artist.Artist;
import com.connor.demo.model.artist.ArtistRating;
import com.connor.demo.model.User;
import com.connor.demo.model.friend.Friend;
import com.connor.demo.model.movie.MovieRating;
import com.connor.demo.service.FriendService;
import com.connor.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private UserService userService;
    private FriendService friendService;

    @Autowired
    public UserController(UserService userService, FriendService friendService) {
        this.userService = userService;
        this.friendService = friendService;
    }

    @GetMapping("/users")
    public ResponseEntity<Iterable<User>> getUsers() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
    }

    @GetMapping("/user/{id}/artists")
    public ResponseEntity<Iterable<Artist>> getUserArtistsById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getArtistsByProfileId(id), HttpStatus.OK);
    }

    @GetMapping("/user/{id}/artists/ids")
    public ResponseEntity<Iterable<Long>> getUserArtistsIdsByProfileId(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getArtistsIdsByProfileId(id), HttpStatus.OK);
    }

    @GetMapping("/user/{id}/ratings")
    public ResponseEntity<Iterable<ArtistRating>> getUserRatings(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUserRatings(id), HttpStatus.OK);
    }

    @GetMapping("/user/{id}/album/ratings")
    public ResponseEntity<Iterable<AlbumRating>> getUserAlbumRatings(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUserAlbumRatings(id), HttpStatus.OK);
    }

    @GetMapping("/user/{id}/movie/ratings")
    public ResponseEntity<Iterable<MovieRating>> getUserMovieRatings(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUserMovieRatings(id), HttpStatus.OK);
    }

    @GetMapping("/users/dto")
    public ResponseEntity<Iterable<UserDto>> findAllUserDto() {
        return new ResponseEntity<>(userService.findAllUserDto(), HttpStatus.OK);
    }

    @GetMapping("/users/search")
    public ResponseEntity<Iterable<UserDto>> searchUsers(@RequestParam String searchTerm) {
        return new ResponseEntity<>(userService.searchUsers(searchTerm), HttpStatus.OK);
    }

    @GetMapping("/user/{id}/friends")
    public ResponseEntity<Iterable<Friend>> getUserFriends(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUserFriends(id), HttpStatus.OK);
    }

//    @GetMapping("/user/{id}/friends/ids")
//    public ResponseEntity<Iterable<Long>> getUserFriendIds(HttpServletRequest request) {
//        return new ResponseEntity<>(friendService.findFriendIds(request), HttpStatus.OK);
//    }


//    @GetMapping("/users/test")
//    public ResponseEntity<Iterable<User>> test() {
//        return new ResponseEntity<>(userService.test(), HttpStatus.OK);
//    }


    @PostMapping("/signin")
    @ResponseStatus(HttpStatus.CREATED)
    public User login(@RequestBody User user) {
        return userService.logIn(user);
    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public User signup(@RequestBody User user) {
        return userService.signUp(user);
    }

}