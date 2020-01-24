package com.connor.demo.repository;

import com.connor.demo.model.ArtistRating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRatingRepository extends JpaRepository<ArtistRating, Long> {


}
