package com.connor.demo.controller;

import com.connor.demo.model.artist.ArtistRating;
import com.connor.demo.model.friend.Friend;
import com.connor.demo.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/friends")
public class FriendController {

    private FriendService friendService;

    @Autowired
    public FriendController(FriendService friendService) {
        this.friendService = friendService;
    }

    @PostMapping("/add")
    public ResponseEntity<Friend> findUserArtistRatings(@RequestParam Long userId, @RequestParam Long friendId) {
        return new ResponseEntity<>(friendService.addFriend(userId, friendId), HttpStatus.OK);
    }

    @GetMapping("user/ids")
    public ResponseEntity<Iterable<Long>> getUserFriendIds(HttpServletRequest request) {
        return new ResponseEntity<>(friendService.findFriendIds(request), HttpStatus.OK);
    }


}
