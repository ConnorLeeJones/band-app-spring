package com.connor.demo.repository;

import com.connor.demo.model.artist.Artist;
import com.connor.demo.model.artist.ArtistRating;
import com.connor.demo.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRatingRepository extends JpaRepository<ArtistRating, Long> {

    ArtistRating getArtistRatingByArtistAndUserProfile(Artist artist, UserProfile userProfile);

    Iterable<ArtistRating> getArtistRatingByUserProfile(UserProfile userProfile);

}
