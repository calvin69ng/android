package com.example.tutorial3android.data;

public class Report {
    private int reportId;
    private String username;
    private String title;
    private String comment;

    public Report(String username, String title, String comment) {
        this.username = username;
        this.title = title;
        this.comment = comment;
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }}