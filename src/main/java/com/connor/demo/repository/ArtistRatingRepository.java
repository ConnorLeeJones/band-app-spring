package com.connor.demo.repository;

import com.connor.demo.model.Artist;
import com.connor.demo.model.ArtistRating;
import com.connor.demo.model.User;
import com.connor.demo.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRatingRepository extends JpaRepository<ArtistRating, Long> {

    ArtistRating getArtistRatingByArtistAndUserProfile(Artist artist, UserProfile userProfile);

    Iterable<ArtistRating> getArtistRatingByUserProfile(UserProfile userProfile);

}
