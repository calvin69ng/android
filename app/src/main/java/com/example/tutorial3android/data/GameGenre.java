package com.example.tutorial3android.data;

public class GameGenre {
    private String gameName;
    private String genreName;

    public GameGenre(String gameName, String genreName) {
        this.gameName = gameName;
        this.genreName = genreName;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }}
