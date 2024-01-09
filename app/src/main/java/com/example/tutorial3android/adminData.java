package com.example.tutorial3android;

import java.util.List;

public class adminData {
    private Integer userId; // Change "_id" to "userId" for consistency
    private String gmail;
    private String username;
    private String password;
    private List<String> games;
    private List<String> friends;
    private List<NotificationData> notifications;
    private List<ReportData> reports;

    public adminData(Integer userId, String gmail, String username, String password) {
        this.userId = userId;
        this.gmail = gmail;
        this.username = username;
        this.password = password;
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

    public List<String> getGames() {
        return games;
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

    // Add methods to update or modify the games, friends, notifications, and reports lists if needed
}