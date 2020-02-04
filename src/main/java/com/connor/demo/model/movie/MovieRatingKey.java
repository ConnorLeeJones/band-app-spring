package com.connor.demo.model.movie;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class MovieRatingKey implements Serializable {

    @Column(name = "profile_id")
    private Long profile_id;

    @Column(name = "movie_id")
    private String movie_id;

    public MovieRatingKey() {}

    public MovieRatingKey(Long profile_id, String movie_id) {
        this.profile_id = profile_id;
        this.movie_id = movie_id;
    }

    public Long getProfile_id() {
        return profile_id;
    }

    public void setProfile_id(Long profile_id) {
        this.profile_id = profile_id;
    }

    public String getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(String movie_id) {
        this.movie_id = movie_id;
    }
}
