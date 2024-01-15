package com.example.tutorial3android.data;

public class Game {
    private int gameId;
    private String gameName;
    private double price;
    private String description;

    public Game(String gameName, double price, String description) {
        this.gameName = gameName;
        this.price = price;
        this.description = description;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }}