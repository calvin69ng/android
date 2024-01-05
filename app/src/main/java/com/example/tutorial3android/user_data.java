package com.example.tutorial3android;

import java.util.List;

public class user_data {
    private Integer _id;
    private String gmail;
    private String username;
    private String password;
    private List<String> games;  // Assuming games is a list of game names
    private List<String> friends;  // Assuming friends is a list of friend usernames

    public user_data(Integer _id, String gmail, String username, String password) {
        this._id = _id;
        this.gmail = gmail;
        this.username = username;
        this.password = password;
    }

    public String getGmail() {
        return gmail;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Integer get_id() {
        return _id;
    }

    public List<String> getGames() {
        return games;
    }

    public List<String> getFriends() {
        return friends;
    }

    // Add methods to update or modify the games and friends lists if needed
}
