package com.example.tutorial3android;

import android.widget.EditText;

import java.util.List;

public class GameHelper {
    private EditText gameNameEditText, priceEditText, descriptionEditText;

    public GameHelper(EditText gameNameEditText, EditText priceEditText, EditText descriptionEditText) {
        this.gameNameEditText = gameNameEditText;
        this.priceEditText = priceEditText;
        this.descriptionEditText = descriptionEditText;
    }

    public game_data getGameData(String gameName, double price, String description, List<String> selectedGenres) {
        // Use the provided parameters instead of reading from EditText
        return new game_data(null, gameName, price, description, selectedGenres);
    }

    public void clearFields() {
        gameNameEditText.setText("");
        priceEditText.setText("");
        descriptionEditText.setText("");
    }
}