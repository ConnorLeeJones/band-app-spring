package com.connor.demo.service;

import com.connor.demo.model.movie.Movie;
import com.connor.demo.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    private MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Iterable<Movie> findAll(){return movieRepository.findAll();}

    public Movie findById(String id){return movieRepository.findById(id).orElse(null);}

    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }
}
