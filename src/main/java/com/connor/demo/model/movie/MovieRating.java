package com.connor.demo.model.movie;

import com.connor.demo.model.UserProfile;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class MovieRating {

    @EmbeddedId
    private MovieRatingKey id;

    @ManyToOne
    @MapsId("profile_id")
    @JoinColumn(name = "profile_id")
    private UserProfile userProfile;

    @ManyToOne
    @MapsId("movie_id")
    @JoinColumn(name = "movie_id")
    private Movie movie;

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

    public MovieRating() {}

    public MovieRating(MovieRatingKey id, UserProfile userProfile, Movie movie, int rating) {
        this.id = id;
        this.userProfile = userProfile;
        this.movie = movie;
        this.rating = rating;
        if (movie != null)
            this.name = movie.getTitle();
    }


    public MovieRatingKey getId() {
        return id;
    }

    public void setId(MovieRatingKey id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
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
