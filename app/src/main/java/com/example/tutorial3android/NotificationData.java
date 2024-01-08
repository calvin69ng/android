package com.example.tutorial3android;

import java.util.Date;
import java.util.List;

public class NotificationData {
    private Integer id;
    private String sender;
    private String message;
    private Date date;
    private List<String> receivers;

    public NotificationData(Integer id, String sender, String message, Date date, List<String> receivers) {
        this.id = id;
        this.sender = sender;
        this.message = message;
        this.date = date;
        this.receivers = receivers;
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

    public List<String> getReceivers() {
        return receivers;
    }
}
