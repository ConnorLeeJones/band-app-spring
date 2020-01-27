package com.connor.demo.service;

import com.connor.demo.model.UserProfile;
import com.connor.demo.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserProfileService {

    private UserProfileRepository repository;

    @Autowired
    public UserProfileService(UserProfileRepository repository) {
        this.repository = repository;
    }


}
