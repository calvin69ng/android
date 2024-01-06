package com.example.tutorial3android;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

public class buygameActivity extends AppCompatActivity {

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
        Button buyButton = findViewById(R.id.buygame);

        final game_data[] selectedGameData = {null}; // Use an array to hold the reference

        // Retrieve selected game data from the intent
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("selectedGameData")) {
            selectedGameData[0] = (game_data) intent.getSerializableExtra("selectedGameData");

            if (selectedGameData[0] != null) {
                Log.d(TAG, "onCreate: Selected Game Data - Name: " + selectedGameData[0].getName()
                        + ", Price: " + selectedGameData[0].getPrice()
                        + ", Description: " + selectedGameData[0].getDescription());

                // Display game information
                gameTitleTextView.setText(selectedGameData[0].getName());
                priceTextView.setText(String.valueOf(selectedGameData[0].getPrice()));
                descriptionTextView.setText(selectedGameData[0].getDescription());
            } else {
                Log.e(TAG, "onCreate: Selected Game Data is null");
            }
        } else {
            Log.e(TAG, "onCreate: Intent or selectedGameData is null");
        }

        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedGameData[0] != null) {
                    // Start MainActivity with the intent and pass the selected game's data
                    Intent mainIntent = new Intent(buygameActivity.this, MainActivity.class);
                    mainIntent.putExtra("selectedGameData", (Serializable) selectedGameData[0]);
                    startActivity(mainIntent);

                    // You can choose whether to finish buygameActivity here or not
                    // finish();
                } else {
                    // Handle the case where selectedGameData is not initialized
                    Log.e(TAG, "onClick: selectedGameData is not initialized");
                }
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
