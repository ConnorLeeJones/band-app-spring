package com.connor.demo.model;

import javax.persistence.Column;


public class UserDto {

    private Long user_id;

    private String username;

    public UserDto(Long user_id, String username) {
        this.user_id = user_id;
        this.username = username;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
