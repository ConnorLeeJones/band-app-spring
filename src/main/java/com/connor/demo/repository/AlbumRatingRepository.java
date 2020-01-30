package com.connor.demo.repository;

import com.connor.demo.model.album.Album;
import com.connor.demo.model.album.AlbumRating;
import com.connor.demo.model.artist.Artist;
import com.connor.demo.model.artist.ArtistRating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRatingRepository extends JpaRepository<AlbumRating, Long> {

    Page<AlbumRating> findByUserProfileProfileId(Long id, Pageable pageable);
    AlbumRating findByAlbumAndUserProfileProfileId(Album album, Long profileId);


}
