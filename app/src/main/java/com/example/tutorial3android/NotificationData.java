package com.example.tutorial3android;

import java.util.Date;

public class NotificationData {
    private Integer id;
    private String sender;
    private String message;
    private Date date;

    public NotificationData(Integer id, String sender, String message, Date date) {
        this.id = id;
        this.sender = sender;
        this.message = message;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public String getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
    }

    public Date getDate() {
        return date;
    }
}
