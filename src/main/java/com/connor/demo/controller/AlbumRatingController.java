package com.connor.demo.controller;


import com.connor.demo.model.album.Album;
import com.connor.demo.model.album.AlbumRating;
import com.connor.demo.service.AlbumRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AlbumRatingController {

    private AlbumRatingService albumRatingService;


    @Autowired
    public AlbumRatingController(AlbumRatingService albumRatingService) {
        this.albumRatingService = albumRatingService;
    }


    @GetMapping("/album/ratings")
    public ResponseEntity<Iterable<AlbumRating>> findAll() {
        return new ResponseEntity<>(albumRatingService.findAll(), HttpStatus.OK);
    }

    @PostMapping("album/ratings/{rating}")
    public ResponseEntity<AlbumRating> addAlbum(HttpServletRequest request, @RequestBody Album
            album, @PathVariable int rating) {
        return new ResponseEntity<>(albumRatingService.addRating(request, album, rating), HttpStatus.OK);
    }

    @GetMapping("album/ratings/{id}")
    public ResponseEntity<AlbumRating> findUserAlbumRating(HttpServletRequest request, @PathVariable Long id) {
        return new ResponseEntity<>(albumRatingService.findUserAlbumRating(request, id), HttpStatus.OK);
    }

}