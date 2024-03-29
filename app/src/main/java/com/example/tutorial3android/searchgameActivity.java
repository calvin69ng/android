package com.example.tutorial3android;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutorial3android.manager.GameManager;

import java.util.ArrayList;
import java.util.List;

public class searchgameActivity extends AppCompatActivity {

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

        recyclerView = findViewById(R.id.playgamelist);
        gameManager = new GameManager(this);

        // Retrieve all game names from the GameManager
        originalGameNames = gameManager.getAllGameNames();

        adapter = new GameListAdapter(originalGameNames);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter.setOnItemClickListener(new GameListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String selectedGameName = originalGameNames.get(position);
                startbuygameActivity(selectedGameName);
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

        // Button for navigating back
        Button backButton = findViewById(R.id.button15);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Back button clicked");
                Intent intent = new Intent(searchgameActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
    }


    private void filterList(String query) {
        Log.d(TAG, "filterList: Filtering with query: " + query);
        List<String> filteredList = new ArrayList<>();

        for (String gameName : originalGameNames) {
            if (gameName != null && (query == null || gameName.toLowerCase().contains(query.toLowerCase()))) {
                filteredList.add(gameName);
            }
        }

        Log.d(TAG, "filterList: Original list size: " + originalGameNames.size());
        Log.d(TAG, "filterList: Filtered list size: " + filteredList.size());

        for (String name : filteredList) {
            Log.d(TAG, "filterList: Filtered game name: " + name);
        }

        // Update the adapter with the filtered list
        adapter.updateList(filteredList);
    }

    private void startbuygameActivity(String selectedGameName) {
        // Create an intent to start buygameActivity
        Intent intent = new Intent(searchgameActivity.this, buygameActivity.class);

        // Pass the selected game name as an extra to the intent
        intent.putExtra("selectedGameName", selectedGameName);

        // Start the buygameActivity with the intent
        startActivity(intent);
    }
}
