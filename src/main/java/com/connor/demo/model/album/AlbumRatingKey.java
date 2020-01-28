package com.connor.demo.model.album;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class AlbumRatingKey implements Serializable {

    @Column(name = "profile_id")
    private Long profile_id;

    @Column(name = "album_id")
    private Long albumId;

    public AlbumRatingKey() {}

    public AlbumRatingKey(Long profile_id, Long albumId) {
        this.profile_id = profile_id;
        this.albumId = albumId;
    }


    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public Long getProfile_id() {
        return profile_id;
    }

    public void setProfile_id(Long profile_id) {
        this.profile_id = profile_id;
    }
}