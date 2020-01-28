package com.connor.demo.model.album;

import com.connor.demo.model.UserProfile;
import com.connor.demo.model.artist.Artist;
import com.connor.demo.model.artist.ArtistRatingKey;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class AlbumRating {

    @EmbeddedId
    @JsonIgnore
    private AlbumRatingKey id;

    @ManyToOne
    @MapsId("profile_id")
    @JoinColumn(name = "profile_id")
    private UserProfile userProfile;

    @ManyToOne
    @MapsId("album_id")
    @JoinColumn(name = "album_id")
    private Album album;

    @Column(name = "rating")
    private int rating;

    @CreationTimestamp
    @Column(name = "created_date", updatable=false)
    private Timestamp createdDate;

    @Column(name = "modified_date")
    @UpdateTimestamp
    private Timestamp modifiedDate;

    public AlbumRating() {}

    public AlbumRating(AlbumRatingKey id, UserProfile userProfile, Album album, int rating) {
        this.id = id;
        this.userProfile = userProfile;
        this.album = album;
        this.rating = rating;
    }

    public AlbumRating(UserProfile userProfile, Album album, int rating) {
        this.userProfile = userProfile;
        this.album = album;
        this.rating = rating;
    }


    public AlbumRatingKey getId() {
        return id;
    }

    public void setId(AlbumRatingKey id) {
        this.id = id;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
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
}
