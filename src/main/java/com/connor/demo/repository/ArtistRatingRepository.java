package com.connor.demo.repository;

import com.connor.demo.model.artist.Artist;
import com.connor.demo.model.artist.ArtistRating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ArtistRatingRepository extends JpaRepository<ArtistRating, Long> {

    Page<ArtistRating> findAll(Pageable pageable);
    Page<ArtistRating> findByUserProfileProfileId(Long id, Pageable pageable);
    ArtistRating findByArtistAndUserProfileProfileId(Artist artist, Long profileId);

    @Query(value = "SELECT * FROM Music.artist_rating r WHERE r.profile_id IN (:ids)", nativeQuery = true)
    Page<ArtistRating> findByUserProfileFriends(@Param("ids") List<Long> ids, Pageable pageable);

}
