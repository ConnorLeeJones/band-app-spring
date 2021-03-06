package com.connor.demo.controller;


import com.connor.demo.model.artist.Artist;
import com.connor.demo.model.artist.ArtistRating;
import com.connor.demo.service.ArtistRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ArtistRatingController {

    private ArtistRatingService artistRatingService;


    @Autowired
    public ArtistRatingController(ArtistRatingService artistRatingService) {
        this.artistRatingService = artistRatingService;
    }


    @GetMapping("/ratings/all")
    public ResponseEntity<Iterable<ArtistRating>> findAll() {
        return new ResponseEntity<>(artistRatingService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/ratings/recent")
    public ResponseEntity<Iterable<ArtistRating>> findRecent() {
        return new ResponseEntity<>(artistRatingService.findRecent(), HttpStatus.OK);
    }

    @PostMapping("/ratings/{rating}")
    public ResponseEntity<ArtistRating> addArtist(HttpServletRequest request, @RequestBody Artist artist, @PathVariable int rating) {
        return new ResponseEntity<>(artistRatingService.addRating(request, artist, rating), HttpStatus.OK);
    }

    @GetMapping("/ratings/{id}")
    public ResponseEntity<ArtistRating> findUserArtistRating(HttpServletRequest request, @PathVariable Long id) {
        return new ResponseEntity<>(artistRatingService.findUserArtistRating(request, id), HttpStatus.OK);
    }

    @GetMapping("artists/ratings")
    public ResponseEntity<Iterable<ArtistRating>> findUserArtistRatings(@RequestParam Long id,
                                                            @RequestParam(defaultValue = "0") Integer pageNo,
                                                              @RequestParam(defaultValue = "6") Integer pageSize,
                                                              @RequestParam(defaultValue = "rating") String sortBy) {
        return new ResponseEntity<>(artistRatingService.getUserArtistRatings(id, pageNo, pageSize, sortBy), HttpStatus.OK);
    }

    @GetMapping("artists/ratings/friends")
    public ResponseEntity<Iterable<ArtistRating>> findFriendsRecentArtistRatings(HttpServletRequest request,
                                                                        @RequestParam(defaultValue = "0") Integer pageNo,
                                                                        @RequestParam(defaultValue = "6") Integer pageSize) {
        return new ResponseEntity<>(artistRatingService.findRecentFriendRatings(request, pageNo, pageSize), HttpStatus.OK);
    }

}
