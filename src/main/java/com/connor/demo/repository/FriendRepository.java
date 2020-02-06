package com.connor.demo.repository;

import com.connor.demo.model.friend.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FriendRepository extends JpaRepository<Friend, Long> {

//    @Query(value = "SELECT f.friend_id FROM Friend f")
    @Query(value = "SELECT f.friend_id FROM Music.friend f WHERE f.profile_id=?1",
        nativeQuery = true)
    Iterable<Long> findFriendIds(Long userId);



}
