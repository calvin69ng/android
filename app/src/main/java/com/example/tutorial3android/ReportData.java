package com.example.tutorial3android;

import java.util.Date;

public class ReportData {
    private Integer id;
    private Integer userId;
    private Date date;
    private String type;
    private String description;

    public ReportData(Integer id, Integer userId, Date date, String type, String description) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.type = type;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public Date getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
}
