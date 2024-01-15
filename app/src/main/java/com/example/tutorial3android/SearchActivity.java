package com.example.tutorial3android;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.tutorial3android.manager.GenreManager;
import com.example.tutorial3android.data.Genre;

import java.util.ArrayList;
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

        // Fetch genres from the database using Cursor
        Cursor cursor = genreManager.getAllGenres();

        // Create a list to store Genre objects
        List<Genre> genreList = new ArrayList<>();

        // Iterate through the cursor and populate the list
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String genreName = cursor.getString(cursor.getColumnIndexOrThrow(GenreManager.COL_GENRE_NAME));
                Genre genre = new Genre(genreName);
                genreList.add(genre);
            } while (cursor.moveToNext());
        }

        // Close the cursor
        if (cursor != null) {
            cursor.close();
        }

        // Log the data to verify
        for (Genre genre : genreList) {
            Log.d("SearchActivity", "Genre: " + genre.getGenreName());
        }

        // Log the size and contents of the genreList
        Log.d("SearchActivity", "GenreList size: " + genreList.size());
        Log.d("SearchActivity", "GenreList contents: " + genreList);

        // Create an adapter and connect it to the ListView
        ClickGenreAdapter adapter = new ClickGenreAdapter(this, genreList);
        ListView listView = findViewById(R.id.listview5); // Replace with your actual ListView id
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedGenreName = genreList.get(position).getGenreName();
                // Log the selected genre name
                Log.d("SearchActivity", "Selected Genre: " + selectedGenreName);
                // Start genre_game_list_Activity and pass the selected genre name
                Intent intent = new Intent(SearchActivity.this, genre_game_list_Activity.class);
                intent.putExtra("selectedGenreName", selectedGenreName);
                startActivity(intent);
            }
        });

        Button search_menu = findViewById(R.id.search_menu);
        search_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchActivity.this, usermenuActivity.class);
                startActivity(intent);
            }
        });

        Button search_game = findViewById(R.id.search_menu2);
        search_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchActivity.this, searchgameActivity.class);
                startActivity(intent);
            }
        });
    }
}