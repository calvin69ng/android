package com.example.tutorial3android;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tutorial3android.data.Game;
import com.example.tutorial3android.manager.GameManager;

import java.io.Serializable;

public class buygameActivity extends AppCompatActivity {

    private static final String TAG = "buygame";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buygame);

        // Assuming you have TextViews and Button in your layout file with these IDs
        TextView gameTitleTextView = findViewById(R.id.textView9);
        TextView priceTextView = findViewById(R.id.textView75);
        TextView descriptionTextView = findViewById(R.id.textView70);
        Button mainMenuButton = findViewById(R.id.csgo_menu2);
        Button backButton = findViewById(R.id.csgo_fps2);
        Button buyButton = findViewById(R.id.buygame);

        // Retrieve the game name from the intent
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("selectedGameName")) {
            String selectedGameName = intent.getStringExtra("selectedGameName");

            // Use the GameManager to get the game information
            GameManager gameManager = new GameManager(this);
            Game selectedGame = gameManager.getGameByName(selectedGameName);

            if (selectedGame != null) {
                // Display game information
                gameTitleTextView.setText(selectedGame.getGameName());
                priceTextView.setText(String.valueOf(selectedGame.getPrice()));
                descriptionTextView.setText(selectedGame.getDescription());
            } else {
                Log.e(TAG, "onCreate: Selected Game is null");
            }
        } else {
            Log.e(TAG, "onCreate: Intent or selectedGameName is null");
        }

        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve the displayed game name and price
                String displayedGameName = gameTitleTextView.getText().toString();
                double displayedGamePrice = Double.parseDouble(priceTextView.getText().toString());

                // Pass the displayed game data to MainActivity
                Intent mainIntent = new Intent(buygameActivity.this, MainActivity.class);
                mainIntent.putExtra("selectedGameName", displayedGameName);
                mainIntent.putExtra("selectedGamePrice", displayedGamePrice);
                startActivity(mainIntent);
            }
        });

        mainMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(buygameActivity.this, usermenuActivity.class);
                startActivity(intent);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(buygameActivity.this, searchgameActivity.class);
                startActivity(intent);
            }
        });
    }
}
