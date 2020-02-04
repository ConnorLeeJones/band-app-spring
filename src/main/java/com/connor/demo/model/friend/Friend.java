package com.connor.demo.model.friend;

import com.connor.demo.model.UserProfile;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Friend {

    @EmbeddedId
    private FriendKey id;

    @ManyToOne
    @MapsId("profile_id")
    @JoinColumn(name = "profile_id")
    private UserProfile userProfile;

    @ManyToOne
    @MapsId("friend_id")
    @JoinColumn(name = "friend_id")
    private UserProfile friendProfile;

    @CreationTimestamp
    @Column(name = "created_date", updatable=false)
    private Timestamp createdDate;

    @Column(name = "modified_date")
    @UpdateTimestamp
    private Timestamp modifiedDate;

    public Friend() {}

    public FriendKey getId() {
        return id;
    }

    public void setId(FriendKey id) {
        this.id = id;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public UserProfile getFriendProfile() {
        return friendProfile;
    }

    public void setFriendProfile(UserProfile friendProfile) {
        this.friendProfile = friendProfile;
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
}
