package com.example.tutorial3android;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tutorial3android.helper.DatabaseHelper;
import com.example.tutorial3android.manager.GenreManager;

public class addgenreActivity extends AppCompatActivity {

    private EditText genreNameEditText;
    private Button backButton, viewButton, addButton;
    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;
    private GenreManager genreManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addgenre);

        genreNameEditText = findViewById(R.id.editTextText11);
        backButton = findViewById(R.id.button12);
        viewButton = findViewById(R.id.button13);
        addButton = findViewById(R.id.button11);

        dbHelper = new DatabaseHelper(this);
        database = dbHelper.getWritableDatabase();
        genreManager = new GenreManager(this); // Initialize GenreManager

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertGenre();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(addgenreActivity.this, adminpage.class);
                startActivity(intent);
            }
        });

        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(addgenreActivity.this, delete_genre_Activity.class);
                startActivity(intent);
            }
        });
    }

    private void insertGenre() {
        String genreName = genreNameEditText.getText().toString();

        if (genreName.isEmpty()) {
            showToast("Genre name cannot be empty");
            return;
        }

        try {
            if (isGenreExists(genreName)) {
                showToast("Genre already exists");
            } else {
                ContentValues values = new ContentValues();
                values.put("genre_name", genreName);

                long newRowId = database.insert("Genre", null, values);

                if (newRowId != -1) {
                    showToast("Genre added successfully!");
                    clearFields();
                } else {
                    showToast("Failed to add genre.");
                }
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    private boolean isGenreExists(String genreName) {
        return genreManager.isGenreExists(genreName);
    }


    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void clearFields() {
        genreNameEditText.setText("");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbHelper.close();
    }
}
