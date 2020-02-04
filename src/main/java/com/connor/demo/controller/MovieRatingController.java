package com.connor.demo.controller;

import com.connor.demo.model.movie.Movie;
import com.connor.demo.model.movie.MovieRating;
import com.connor.demo.service.MovieRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/movies")
public class MovieRatingController {

    private MovieRatingService movieRatingService;


    @Autowired
    public MovieRatingController(MovieRatingService movieRatingService) {
        this.movieRatingService = movieRatingService;
    }


    @GetMapping("/ratings/all")
    public ResponseEntity<Iterable<MovieRating>> findAll() {
        return new ResponseEntity<>(movieRatingService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/ratings/{rating}")
    public ResponseEntity<MovieRating> addMovie(HttpServletRequest request, @RequestBody Movie
            movie, @PathVariable int rating) {
        return new ResponseEntity<>(movieRatingService.addRating(request, movie, rating), HttpStatus.OK);
    }

    @GetMapping("ratings/{id}")
    public ResponseEntity<MovieRating> findUserMovieRating(HttpServletRequest request, @PathVariable String id) {
        return new ResponseEntity<>(movieRatingService.findUserMovieRating(request, id), HttpStatus.OK);
    }

    @GetMapping("ratings")
    public ResponseEntity<Iterable<MovieRating>> findUserMovieRatings(@RequestParam Long id,
                                                                     @RequestParam(defaultValue = "0") Integer pageNo,
                                                                     @RequestParam(defaultValue = "6") Integer pageSize,
                                                                     @RequestParam(defaultValue = "rating") String sortBy) {
        return new ResponseEntity<>(movieRatingService.getUserMovieRatings(id, pageNo, pageSize, sortBy), HttpStatus.OK);
    }


}
