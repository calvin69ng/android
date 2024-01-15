package com.example.tutorial3android;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tutorial3android.manager.GameManager;

import java.util.ArrayList;
import java.util.List;

public class AddGameActivity extends AppCompatActivity {

    private EditText gameNameEditText, priceEditText, descriptionEditText;
    private Button uploadButton, backButton;
    private GameManager gameManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addgame);

        gameNameEditText = findViewById(R.id.editTextText);
        priceEditText = findViewById(R.id.editTextText2);
        descriptionEditText = findViewById(R.id.editTextText3);
        uploadButton = findViewById(R.id.button4);
        backButton = findViewById(R.id.button5);

        gameManager = new GameManager(this);

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertGameAndNavigate();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddGameActivity.this, adminpage.class);
                startActivity(intent);
            }
        });
    }

    private void insertGameAndNavigate() {
        // Retrieve values from EditText widgets
        String gameName = gameNameEditText.getText().toString();
        String priceStr = priceEditText.getText().toString();
        String description = descriptionEditText.getText().toString();

        if (TextUtils.isEmpty(gameName) || TextUtils.isEmpty(priceStr) || TextUtils.isEmpty(description)) {
            // Show an error message or handle the case where any of the fields is empty
            Toast.makeText(AddGameActivity.this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
            return; // Don't proceed if any field is empty
        }

        // Convert price to double (you can add validation for this as well)
        double price = Double.parseDouble(priceStr);

        // Check if the game already exists
        if (!gameManager.isGameExist(gameName)) {
            // Game doesn't exist, proceed to the next activity

            // Don't insert into the database yet, just navigate to the next activity
            Intent intent = new Intent(AddGameActivity.this, pick_genreActivity.class);
            intent.putExtra("gameName", gameName);
            intent.putExtra("price", price);
            intent.putExtra("description", description);
            startActivity(intent);
        } else {
            // Game already exists, show a message or handle it as needed
            Toast.makeText(AddGameActivity.this, "Game with this name already exists", Toast.LENGTH_SHORT).show();
        }
    }

}
