package com.connor.demo.repository;

import com.connor.demo.model.movie.Movie;
import com.connor.demo.model.movie.MovieRating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRatingRepository extends JpaRepository<MovieRating, Long> {

    Page<MovieRating> findAll(Pageable pageable);
    Page<MovieRating> findByUserProfileProfileId(Long id, Pageable pageable);
    MovieRating findByMovieAndUserProfileProfileId(Movie movie, Long profileId);

}
