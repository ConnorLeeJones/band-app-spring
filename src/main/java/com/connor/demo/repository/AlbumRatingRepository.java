package com.connor.demo.repository;

import com.connor.demo.model.album.Album;
import com.connor.demo.model.album.AlbumRating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlbumRatingRepository extends JpaRepository<AlbumRating, Long> {

    Page<AlbumRating> findByUserProfileProfileId(Long id, Pageable pageable);
    AlbumRating findByAlbumAndUserProfileProfileId(Album album, Long profileId);

    @Query(value = "SELECT * FROM Music.album_rating r WHERE r.profile_id IN (:ids)", nativeQuery = true)
    Page<AlbumRating> findByUserProfileFriends(@Param("ids") List<Long> ids, Pageable pageable);



}
