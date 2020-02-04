package com.connor.demo.model.movie;

import com.connor.demo.model.movie.MovieRating;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
public class Movie {

    @NotNull
    @Id
    @Column(name = "movie_id")
    private Long id;

    @NotNull
    @Column(name = "title")
    private String title;

    @Column(name = "poster")
    private String poster;

    @OneToMany(mappedBy = "movie")
    @JsonIgnore
    private Set<MovieRating> ratings;

    public Movie() {}

    public Movie(@NotNull Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public Set<MovieRating> getRatings() {
        return ratings;
    }

    public void setRatings(Set<MovieRating> ratings) {
        this.ratings = ratings;
    }
}
