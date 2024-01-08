package com.example.tutorial3android;

import java.util.Date;
import java.util.List;

public class IncomeData {
    private String username;
    private double amount;
    private Date time;
    private List<String> games;

    public IncomeData(String username, double amount, Date time, List<String> games) {
        this.username = username;
        this.amount = amount;
        this.time = time;
        this.games = games;
    }

    public String getUsername() {
        return username;
    }

    public double getAmount() {
        return amount;
    }

    public Date getTime() {
        return time;
    }

    public List<String> getGames() {
        return games;
    }
}
