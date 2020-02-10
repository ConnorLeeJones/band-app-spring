package com.connor.demo.repository;

import com.connor.demo.model.movie.Movie;
import com.connor.demo.model.movie.MovieRating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRatingRepository extends JpaRepository<MovieRating, Long> {

    Page<MovieRating> findAll(Pageable pageable);
    Page<MovieRating> findByUserProfileProfileId(Long id, Pageable pageable);
    MovieRating findByMovieAndUserProfileProfileId(Movie movie, Long profileId);

    @Query(value = "SELECT * FROM Music.movie_rating r WHERE r.profile_id IN (:ids)", nativeQuery = true)
    Page<MovieRating> findByUserProfileFriends(@Param("ids") List<Long> ids, Pageable pageable);

}
