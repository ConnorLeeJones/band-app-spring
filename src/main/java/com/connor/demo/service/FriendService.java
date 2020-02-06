package com.connor.demo.service;

import com.connor.demo.model.User;
import com.connor.demo.model.UserProfile;
import com.connor.demo.model.artist.Artist;
import com.connor.demo.model.artist.ArtistRating;
import com.connor.demo.model.artist.ArtistRatingKey;
import com.connor.demo.model.friend.Friend;
import com.connor.demo.model.friend.FriendKey;
import com.connor.demo.repository.FriendRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class FriendService {


    private FriendRepository friendRepository;
    private UserService userService;

    public FriendService(FriendRepository friendRepository, UserService userService) {
        this.friendRepository = friendRepository;
        this.userService = userService;
    }


    public Friend addFriend(Long userId, Long friendId){
        UserProfile userProfile = userService.findUserById(userId).getUserProfile();
        UserProfile friendProfile = userService.findUserById(friendId).getUserProfile();
        Friend friend = new Friend(new FriendKey(userId, friendId), userProfile, friendProfile);
        return friendRepository.save(friend);
    }

    public Iterable<Long> findFriendIds(HttpServletRequest request){
        User user = userService.getUserByToken(request);
        return friendRepository.findFriendIds(user.getId());
    }

}
