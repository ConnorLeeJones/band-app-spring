package com.connor.demo.model.album;

import com.connor.demo.model.UserProfile;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Album {

    @NotNull
    @Id
    @Column(name = "album_id")
    private Long album_id;

    @NotNull
    @Column(name = "title")
    private String title;

    @Column(name = "cover_small")
    private String cover_small;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "albums")
    @JsonIgnore
    private Set<UserProfile> userProfiles = new HashSet<>();

    @OneToMany(mappedBy = "album")
    @JsonIgnore
    private Set<AlbumRating> ratings;


    public Album() {}

    public Album(@NotNull Long id) {
        this.album_id = id;
    }

    public Long getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(Long album_id) {
        this.album_id = album_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover_small() {
        return cover_small;
    }

    public void setCover_small(String cover_small) {
        this.cover_small = cover_small;
    }

    public Set<UserProfile> getUserProfiles() {
        return userProfiles;
    }

    public void setUserProfiles(Set<UserProfile> userProfiles) {
        this.userProfiles = userProfiles;
    }


    public Set<AlbumRating> getRatings() {
        return ratings;
    }

    public void setRatings(Set<AlbumRating> ratings) {
        this.ratings = ratings;
    }
}
