package com.connor.demo.controller;

import com.connor.demo.model.movie.Movie;
import com.connor.demo.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/movies")
public class MovieController {

    private MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Movie>> getMovies() {
        return new ResponseEntity<>(movieService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
        return new ResponseEntity<>(movieService.addMovie(movie), HttpStatus.OK);
    }

}
