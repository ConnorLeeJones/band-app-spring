package com.connor.demo.model;

import javax.persistence.Entity;

import javax.persistence.*;
import javax.validation.constraints.Email;

import com.connor.demo.model.album.Album;
import com.connor.demo.model.album.AlbumRating;
import com.connor.demo.model.artist.Artist;
import com.connor.demo.model.artist.ArtistRating;
import com.connor.demo.model.friend.Friend;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.Cascade;

import java.util.HashSet;
import java.util.Set;

@Entity
public class UserProfile {

    @Id
    @Column(name = "profile_id")
    private Long profileId;


    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "profile_id", nullable = false)
    private User user;


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @Column(name = "artists")
    @JoinTable(
            name = "artist_like",
            joinColumns = @JoinColumn(name = "profile_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id"))
    private Set<Artist> artists = new HashSet<>();


    @OneToMany(mappedBy = "userProfile")
    @JsonIgnore
    private Set<ArtistRating> ratings = new HashSet<>();


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @Column(name = "albums")
    @JoinTable(
            name = "album_like",
            joinColumns = @JoinColumn(name = "profile_id"),
            inverseJoinColumns = @JoinColumn(name = "album_id"))
    private Set<Album> albums = new HashSet<>();


    @OneToMany(mappedBy = "userProfile")
    @JsonIgnore
    private Set<AlbumRating> album_ratings = new HashSet<>();

    @OneToMany(mappedBy = "userProfile")
    @JsonIgnore
    private Set<Friend> friends = new HashSet<>();

    @Column(name = "username")
    private String username;

    @Email
    @Column(name = "email")
    private String email;


    public UserProfile(){}

    public UserProfile(Long profileId){
        this.profileId = profileId;
    }

    public UserProfile(Long profileId, String username){
        this.profileId = profileId;
        this.username = username;
    }


    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Artist> getArtists() {
        return artists;
    }

    public void setArtists(Set<Artist> artists) {
        this.artists = artists;
    }

    public Set<ArtistRating> getRatings() {
        return ratings;
    }

    public void setRatings(Set<ArtistRating> ratings) {
        this.ratings = ratings;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }

    public Set<AlbumRating> getAlbum_ratings() {
        return album_ratings;
    }

    public void setAlbum_ratings(Set<AlbumRating> album_ratings) {
        this.album_ratings = album_ratings;
    }

    public Set<Friend> getFriends() {
        return friends;
    }

    public void setFriends(Set<Friend> friends) {
        this.friends = friends;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
