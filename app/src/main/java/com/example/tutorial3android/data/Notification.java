package com.example.tutorial3android.data;

public class Notification {
    private int notificationId;
    private String sender;
    private String message;
    private String receiver;
    private String time;

    public Notification(String sender, String message, String receiver, String time) {
        this.sender = sender;
        this.message = message;
        this.receiver = receiver;
        this.time = time;
    }

    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }}
