package com.example.tutorial3android;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class buygame extends AppCompatActivity {

    private static final String TAG = "buygame"; // Define TAG here

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
        Button buyButton = findViewById(R.id.csgo_payment2);

        // Retrieve selected game data from the intent
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("selectedGameData")) {
            game_data selectedGameData = (game_data) intent.getSerializableExtra("selectedGameData");

            if (selectedGameData != null) {
                Log.d(TAG, "onCreate: Selected Game Data - Name: " + selectedGameData.getName()
                        + ", Price: " + selectedGameData.getPrice()
                        + ", Description: " + selectedGameData.getDescription());

                // Display game information
                gameTitleTextView.setText(selectedGameData.getName());
                priceTextView.setText(String.valueOf(selectedGameData.getPrice()));
                descriptionTextView.setText(selectedGameData.getDescription());
            } else {
                Log.e(TAG, "onCreate: Selected Game Data is null");
            }
        } else {
            Log.e(TAG, "onCreate: Intent or selectedGameData is null");
        }

        mainMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(buygame.this, usermenuActivity.class);
                startActivity(intent);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(buygame.this, searchgameActivity.class);
                startActivity(intent);
            }
        });
    }
}
