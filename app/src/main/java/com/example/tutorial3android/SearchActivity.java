package com.example.tutorial3android;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        GenreManager genreManager = new GenreManager(this);
        genreManager.open();

        // Fetch genres from the database
        List<GenreData> genreList = genreManager.getAllGenresList();

        // Log the data to verify
        for (GenreData genre : genreList) {
            Log.d("SearchActivity", "Genre: " + genre.getGenres());
        }

        // Create a set of selected genre IDs (assuming GenreData has an identifier like genreId)
        Set<GenreData> selectedGenreIds = new HashSet<>();
        // Create an adapter and connect it to the ListView
        GenreDataAdapter adapter = new GenreDataAdapter(this, genreList, selectedGenreIds);
        ListView listView = findViewById(R.id.listview5); // Replace with your actual ListView id
        listView.setAdapter(adapter);

        Button search_menu = findViewById(R.id.search_menu);
        search_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchActivity.this, usermenuActivity.class);
                startActivity(intent);
            }
        });
    }
}
