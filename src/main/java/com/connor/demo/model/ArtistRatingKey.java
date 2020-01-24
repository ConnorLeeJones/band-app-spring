package com.connor.demo.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ArtistRatingKey implements Serializable {

    @Column(name = "profile_id")
    private Long profile_id;

    @Column(name = "artist_id")
    private Long artistId;

    public ArtistRatingKey() {}

    public ArtistRatingKey(Long profile_id, Long artistId) {
        this.profile_id = profile_id;
        this.artistId = artistId;
    }


    public Long getArtistId() {
        return artistId;
    }

    public void setArtistId(Long artistId) {
        this.artistId = artistId;
    }

    public Long getProfile_id() {
        return profile_id;
    }

    public void setProfile_id(Long profile_id) {
        this.profile_id = profile_id;
    }
}