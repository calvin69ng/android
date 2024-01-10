package com.example.tutorial3android;

import java.util.Date;
import java.util.List;

public class NotificationData {
    private long  id;
    private String sender;
    private String message;
    private Date date;
    private String receiver; // Change the type to String

    public NotificationData(long  id, String sender, String message, Date date, String receiver) {
        this.id = id;
        this.sender = sender;
        this.message = message;
        this.date = date;
        this.receiver = receiver;
    }

    public long  getId() {
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

    public String getReceiver() { // Fix the method name to getReceiver()
        return receiver;
    }
}
