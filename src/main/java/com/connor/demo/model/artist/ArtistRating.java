package com.connor.demo.model.artist;


import com.connor.demo.model.UserProfile;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class ArtistRating {

    @EmbeddedId
    private ArtistRatingKey id;

    @ManyToOne
    @MapsId("profile_id")
    @JoinColumn(name = "profile_id")
    private UserProfile userProfile;

    @ManyToOne
    @MapsId("artist_id")
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @Column(name = "rating")
    private int rating;

    @Column(name = "name")
    private String name;

    @CreationTimestamp
    @Column(name = "created_date", updatable=false)
    private Timestamp createdDate;

    @Column(name = "modified_date")
    @UpdateTimestamp
    private Timestamp modifiedDate;

    public ArtistRating() {}

    public ArtistRating(ArtistRatingKey id, UserProfile userProfile, Artist artist, int rating) {
        this.id = id;
        this.userProfile = userProfile;
        this.artist = artist;
        this.rating = rating;
        if (artist != null)
            this.name = artist.getName();
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

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}