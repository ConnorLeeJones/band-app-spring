package com.connor.demo.model;

import javax.persistence.Entity;

import javax.persistence.*;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JsonBackReference(value = "artists")
    @JsonIgnore
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @Column(name = "artists")
    @JoinTable(
            name = "artist_like",
            joinColumns = @JoinColumn(name = "profile_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id"))
    private Set<Artist> artists = new HashSet<>();


    @Email
    @Column(name = "email")
    private String email;


    public UserProfile(){}

    public UserProfile(Long profileId){
        this.profileId = profileId;
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
}
