package com.connor.demo.repository;

import com.connor.demo.model.album.AlbumRating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRatingRepository extends JpaRepository<AlbumRating, Long> {
}
