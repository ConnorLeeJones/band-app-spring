package com.connor.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class ArtistRating {

    @EmbeddedId
    private ArtistRatingKey id;

    @ManyToOne
    @JsonIgnore
    @MapsId("profile_id")
    @JoinColumn(name = "profile_id")
    private UserProfile userProfile;

    @ManyToOne
    @JsonIgnore
    @MapsId("artist_id")
    @JoinColumn(name = "artist_id")
    private Artist artist;

    private int rating;

    public ArtistRating() {}

    public ArtistRating(UserProfile userProfile, Artist artist, int rating) {
        this.userProfile = userProfile;
        this.artist = artist;
        this.rating = rating;
    }

    public ArtistRatingKey getId() {
        return id;
    }

    public void setId(ArtistRatingKey id) {
        this.id = id;
    }



    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }
}