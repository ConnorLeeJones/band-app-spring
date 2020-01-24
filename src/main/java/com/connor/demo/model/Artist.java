package com.connor.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Artist {


    @NotNull
    @Id
    @Column(name = "artist_id")
    private Long artist_id;

    @NotNull
    @Column(name = "name")
    private String name;

    @Column(name = "picture_small")
    private String picture_small;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "artists")
    @JsonIgnore
//    @JsonManagedReference(value = "artists")
    private Set<UserProfile> userProfiles = new HashSet<>();

    @OneToMany(mappedBy = "artist")
    @JsonIgnore
    private Set<ArtistRating> ratings;


    public Artist() {}


//    public Artist(@NotNull Long id, UserProfile userProfile) {
//        this.id = id;
//        this.userProfile = userProfile;
//    }

    public Artist(@NotNull Long id) {
        this.artist_id = id;
    }


    public Long getId() {
        return artist_id;
    }

    public void setId(Long id) {
        this.artist_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture_small() {
        return picture_small;
    }

    public void setPicture_small(String picture_small) {
        this.picture_small = picture_small;
    }

    public Set<UserProfile> getUserProfile() {
        return userProfiles;
    }

    public void setUserProfile(Set<UserProfile> userProfiles) {
        this.userProfiles = userProfiles;
    }

    public Long getArtist_id() {
        return artist_id;
    }

    public void setArtist_id(Long artist_id) {
        this.artist_id = artist_id;
    }

    public Set<UserProfile> getUserProfiles() {
        return userProfiles;
    }

    public void setUserProfiles(Set<UserProfile> userProfiles) {
        this.userProfiles = userProfiles;
    }

    public Set<ArtistRating> getRatings() {
        return ratings;
    }

    public void setRatings(Set<ArtistRating> ratings) {
        this.ratings = ratings;
    }
}
