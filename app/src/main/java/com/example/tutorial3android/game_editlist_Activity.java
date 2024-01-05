package com.example.tutorial3android;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class game_editlist_Activity extends AppCompatActivity {

    private static final String TAG = "GameEditListActivity";

    private RecyclerView recyclerView;
    private GameManager gameManager;
    private List<String> originalGameNames;
    private GameListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_editlist);

        Log.d(TAG, "onCreate: Activity created");

        recyclerView = findViewById(R.id.recyclerView);
        gameManager = new GameManager(this);

        originalGameNames = gameManager.getAllGameNames();

        adapter = new GameListAdapter(originalGameNames);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter.setOnItemClickListener(new GameListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String selectedGameName = originalGameNames.get(position);
                startDeleteGameActivity(selectedGameName);
            }
        });

        EditText editTextSearch = findViewById(R.id.editTextSearch);

        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                Log.d(TAG, "onTextChanged: Text changed to " + charSequence.toString());
                filterList(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        Button backButton = findViewById(R.id.button15);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Back button clicked");
                Intent intent = new Intent(game_editlist_Activity.this, adminpage.class);
                startActivity(intent);
            }
        });
    }

    private void filterList(String query) {
        Log.d(TAG, "filterList: Filtering with query: " + query);
        List<String> filteredList = new ArrayList<>();

        if (query == null || query.isEmpty()) {
            filteredList.addAll(originalGameNames);
        } else {
            for (String gameName : originalGameNames) {
                if (gameName != null && gameName.toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(gameName);
                }
            }
        }

        Log.d(TAG, "filterList: Filtered list size: " + filteredList.size());

        // Delete games with empty or null names
        deleteGamesWithEmptyNames(filteredList);

        adapter.updateList(filteredList);
    }

    private void deleteGamesWithEmptyNames(List<String> gameNames) {
        for (String gameName : gameNames) {
            if (TextUtils.isEmpty(gameName)) {
                // Get the game by name
                game_data gameToDelete = gameManager.getGameByName(gameName);

                // Check if the game exists
                if (gameToDelete != null) {
                    // Delete the game by its ID
                    gameManager.deleteGameById(gameToDelete.get_id());
                }
            }
        }
    }

    private void startDeleteGameActivity(String selectedGameName) {
        game_data selectedGameData = gameManager.getGameByName(selectedGameName);

        Intent intent = new Intent(game_editlist_Activity.this, DeletegameActivity.class);
        intent.putExtra("selectedGameData", selectedGameData);
        startActivity(intent);
    }
}
