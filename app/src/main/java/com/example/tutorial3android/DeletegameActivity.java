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
    private Button backButton, deleteButton, editButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deletegame);


        editButton = findViewById(R.id.button16);
        backButton = findViewById(R.id.button17);
        deleteButton = findViewById(R.id.button18);

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

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve the game name from the EditText field
                String gameName = gameNameEditText.getText().toString();

                // Delete the game data from SQLite
                boolean success = gameManager.deleteGameByName(gameName);

                if (success) {
                    // If deletion is successful, go back to game_editlist_Activity
                    Intent intent = new Intent(DeletegameActivity.this, game_editlist_Activity.class);
                    startActivity(intent);
                    finish(); // Finish the current activity to prevent going back to it from the next activity
                } else {
                    // Handle deletion failure, e.g., display a toast
                    gameManager.showToast("Failed to delete the game");
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DeletegameActivity.this, game_editlist_Activity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
