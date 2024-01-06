package com.example.tutorial3android;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity"; // Define TAG here

    private RecyclerView recyclerView;
    private GameNamePriceAdapter adapter;
    private List<game_data> selectedGamesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.buywhatgame);

        // Set up the adapter with OnItemClickListener
        adapter = new GameNamePriceAdapter(selectedGamesList, new GameNamePriceAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // Handle item click here, if needed
            }
        });

        // Set the adapter to the RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Initialize buttons and set click listeners
        Button fpx = findViewById(R.id.fpx);
        fpx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, fpxActivity.class);
                startActivity(intent);
            }
        });

        Button debit = findViewById(R.id.debit);
        debit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, debit_cardActivity.class);
                startActivity(intent);
            }
        });

        Button payment_menu = findViewById(R.id.payment_menu);
        payment_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, usermenuActivity.class);
                startActivity(intent);
            }
        });

        Button payment_tng = findViewById(R.id.payment_tng);
        payment_tng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, usermenuActivity.class);
                startActivity(intent);
            }
        });
    }

    // Method to get the selected game list
    public List<game_data> getSelectedGamesList() {
        return selectedGamesList;
    }

    // Method to update the RecyclerView
    public void updateRecyclerView() {
        adapter.updateList(selectedGamesList);
    }

    // Method to add selected game to the list and update the RecyclerView
    public void addSelectedGameToRecyclerView(String selectedGameName, double selectedGamePrice) {
        // Create a new game_data instance with the provided name and price
        // Assuming the constructor is (Integer _id, String name, double price, String description, List<String> genres)
        game_data selectedGameData = new game_data(null, selectedGameName, selectedGamePrice, "", null);

        // Add the selected game to the list
        selectedGamesList.add(selectedGameData);

        // Update the RecyclerView
        updateRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("selectedGameData")) {
            game_data selectedGameData = (game_data) intent.getSerializableExtra("selectedGameData");

            if (selectedGameData != null) {
                Log.d(TAG, "onResume: Received Game Data - Name: " + selectedGameData.getName()
                        + ", Price: " + selectedGameData.getPrice()
                        + ", Description: " + selectedGameData.getDescription());

                // Add the selected game to the list
                addSelectedGameToRecyclerView(selectedGameData.getName(), selectedGameData.getPrice());
            }
        }
    }
}
