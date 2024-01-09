package com.example.tutorial3android;

import java.util.List;

public class UserData {
    private Integer userId; // Change "_id" to "userId" for consistency
    private String gmail;
    private String username;
    private String password;
    private List<game_data> games;
    private List<String> friends;
    private List<NotificationData> notifications;
    private List<ReportData> reports;

    public UserData(Integer userId, String gmail, String username, String password, List<game_data> games) {
        this.userId = userId;
        this.gmail = gmail;
        this.username = username;
        this.password = password;
        this.games = games;
    }

    public Integer getUserId() {
        return userId;
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

    public List<game_data> getGames() {
        return games;
    }

    public void setGames(List<game_data> games) {
        this.games = games;
    }

    public List<String> getFriends() {
        return friends;
    }

    public List<NotificationData> getNotifications() {
        return notifications;
    }

    public List<ReportData> getReports() {
        return reports;
    }

    // Add methods to update or modify the friends, notifications, and reports lists if needed
}