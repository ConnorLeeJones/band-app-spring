package com.connor.demo.model.friend;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class FriendKey implements Serializable {

    @Column(name = "profile_id")
    private Long profile_id;

    @Column(name = "friend_id")
    private Long friend_id;

    public FriendKey() {}


    public FriendKey(Long profile_id, Long friend_id) {
        this.profile_id = profile_id;
        this.friend_id = friend_id;
    }

    public Long getProfile_id() {
        return profile_id;
    }

    public void setProfile_id(Long profile_id) {
        this.profile_id = profile_id;
    }

    public Long getFriend_id() {
        return friend_id;
    }

    public void setFriend_id(Long friend_id) {
        this.friend_id = friend_id;
    }
}
