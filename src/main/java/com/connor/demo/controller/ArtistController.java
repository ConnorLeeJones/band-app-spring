package com.connor.demo.controller;

import com.connor.demo.model.artist.Artist;
import com.connor.demo.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ArtistController {

    private ArtistService artistService;

    @Autowired
    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping("/artists")
    public ResponseEntity<Iterable<Artist>> getUsers() {
        return new ResponseEntity<>(artistService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/artists/{id}")
    public ResponseEntity<Iterable<Artist>> getUsersByProfile(@PathVariable Long id) {
        return new ResponseEntity<>(artistService.findAll(), HttpStatus.OK);
    }

    @PutMapping("/artist/{id}")
    public ResponseEntity<Artist> unfollowArtist(HttpServletRequest request, @PathVariable Long id) {
        return new ResponseEntity<>(artistService.unfollowArtist(request, id), HttpStatus.OK);
    }

    @PostMapping("/artists")
    public ResponseEntity<Artist> addArtist(HttpServletRequest request, @RequestBody Artist artist) {
        return new ResponseEntity<>(artistService.addArtist(request, artist), HttpStatus.OK);
    }


}
