package com.connor.demo.repository;

import com.connor.demo.model.artist.ArtistRating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ArtistRatingRepository extends JpaRepository<ArtistRating, Long> {

    Page<ArtistRating> findByUserProfileProfileId(Long id, Pageable pageable);

}
