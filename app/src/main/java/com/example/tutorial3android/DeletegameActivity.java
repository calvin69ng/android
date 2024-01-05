package com.example.tutorial3android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class DeletegameActivity extends AppCompatActivity {

    private EditText gameNameEditText, gamepriceEditText, gamedescriptionEditText;
    private GameManager gameManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deletegame);

        gameNameEditText = findViewById(R.id.gamename);
        gamepriceEditText = findViewById(R.id.price);
        gamedescriptionEditText = findViewById(R.id.description);
        gameManager = new GameManager(this);

        // Retrieve the selected game data from the intent
        game_data selectedGameData = getIntent().getParcelableExtra("selectedGameData");

        // Populate the EditText with the selected game data
        if (selectedGameData != null) {
            gameNameEditText.setText(selectedGameData.getName());
            gamepriceEditText.setText(String.valueOf(selectedGameData.getPrice()));
            gamedescriptionEditText.setText(selectedGameData.getDescription());
        }

        Button deleteButton = findViewById(R.id.button18);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve the game name from the EditText field
                String gameName = gameNameEditText.getText().toString();

                // Delete the game data from SQLite
                boolean success = gameManager.deleteGameByName(gameName);

                if (success) {
                    Intent intent = new Intent(DeletegameActivity.this, game_editlist_Activity.class);
                    startActivity(intent);
                } else {
                    // Handle deletion failure, e.g., display a toast
                    gameManager.showToast("Failed to delete the game");
                }
            }
        });
    }
}
