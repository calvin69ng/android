package com.example.tutorial3android;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tutorial3android.data.Game;
import com.example.tutorial3android.data.GameGenre;
import com.example.tutorial3android.data.Genre;
import com.example.tutorial3android.manager.GameManager;
import com.example.tutorial3android.manager.GameGenreManager;
import com.example.tutorial3android.manager.GenreManager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class pick_gerne2Activity extends AppCompatActivity {

    private static final String TAG = "pick_genre2Activity";

    private ListView genreListView;
    private Button backButton, nextButton;
    private GenreManager genreManager;
    private List<Genre> genreList;
    private PickGerne2DataAdapter adapter;
    private Set<Genre> selectedGenres = new HashSet<>();
    private String gameName;
    private double price;
    private String description;
    private long selectedGameId;
    private String selectedGameName;
    private List<Genre> preSelectedGenres = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_genre);

        genreListView = findViewById(R.id.listViewGenre119);
        backButton = findViewById(R.id.button7);
        nextButton = findViewById(R.id.button8);

        genreManager = new GenreManager(this);
        genreManager.open();

        Intent intent = getIntent();
        selectedGameName = getIntent().getStringExtra("selectedGameName");
        String updatedGameName = intent.getStringExtra("updatedGameName");
        double updatedGamePrice = intent.getDoubleExtra("updatedGamePrice", 0.0);
        String updatedGameDescription = intent.getStringExtra("updatedGameDescription");

        Game updatedGameData = new Game(updatedGameName, updatedGamePrice, updatedGameDescription);

        initializeViews();
        setupListeners();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (genreManager != null) {
            genreManager.close();
        }
    }

    private void initializeViews() {
        genreList = genreManager.getAllGenresList();

        List<String> selectedGenresForGame = new GameGenreManager(this).getGameNamesByGenre(selectedGameName);

        for (String genreName : selectedGenresForGame) {
            Log.d(TAG, "Selected genre for the game: " + genreName);
        }
        for (Genre genre : genreList) {
            if (genre != null) {
                Log.d(TAG, "Genre: " + genre.getGenreName());

                if (selectedGenresForGame.contains(genre.getGenreName())) {
                    genre.setSelected(true);
                } else {
                    genre.setSelected(false);
                }
            } else {
                Log.d(TAG, "Genre is null");
            }
        }

        adapter = new PickGerne2DataAdapter(this, genreList, selectedGenres);
        genreListView.setAdapter(adapter);
    }

    private void setupListeners() {
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToAddGenreDeletegame();
            }
        });

        genreListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                handleGenreSelection(view, position);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToAdminPage();
            }
        });
    }

    private void navigateToAddGenreDeletegame() {
        Intent intent = new Intent(pick_gerne2Activity.this, game_editlist_Activity.class);
        startActivity(intent);
    }

    private void handleGenreSelection(View view, int position) {
        Genre selectedGenre = genreList.get(position);
        Log.d(TAG, "Selected genre: " + selectedGenre.getGenreName());

        boolean isRelatedToSelectedGame = new GameGenreManager(this).getGameNamesByGenre(selectedGameName)
                .contains(selectedGenre.getGenreName());

        if (selectedGenre.isSelected()) {
            selectedGenres.remove(selectedGenre);
            selectedGenre.setSelected(false);
            view.setBackgroundColor(getResources().getColor(isRelatedToSelectedGame ? R.color.selected_color : R.color.white));
            Log.d(TAG, "Deselected genre: " + selectedGenre.getGenreName());
        } else {
            selectedGenres.add(selectedGenre);
            selectedGenre.setSelected(true);
            view.setBackgroundColor(getResources().getColor(R.color.selected_color));
            Log.d(TAG, "Selected genre: " + selectedGenre.getGenreName());
        }

        adapter.updateData(selectedGenres);
    }

    private void navigateToAdminPage() {
        if (!selectedGenres.isEmpty()) {
            Game updatedGameData = getIntent().getParcelableExtra("updatedGameData");

            long gameId = new GameManager(pick_gerne2Activity.this).updateGame(selectedGameName, updatedGameData);

            if (gameId != -1) {
                new GameGenreManager(pick_gerne2Activity.this).deleteGenresByGameName(selectedGameName);

                for (Genre selectedGenre : selectedGenres) {
                    new GameGenreManager(pick_gerne2Activity.this).insertGameGenre(new GameGenre(updatedGameData.getGameName(), selectedGenre.getGenreName()));
                }

                Intent adminIntent = new Intent(pick_gerne2Activity.this, game_editlist_Activity.class);
                adminIntent.putExtra("gameId", gameId);
                startActivity(adminIntent);
            } else {
                Toast.makeText(pick_gerne2Activity.this, "Failed to update game information", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(pick_gerne2Activity.this, "Please select at least one genre", Toast.LENGTH_SHORT).show();
        }
    }
}
