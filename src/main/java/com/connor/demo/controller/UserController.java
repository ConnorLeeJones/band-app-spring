package com.connor.demo.controller;


import com.connor.demo.model.artist.Artist;
import com.connor.demo.model.artist.ArtistRating;
import com.connor.demo.model.User;
import com.connor.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
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