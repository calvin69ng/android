package com.example.tutorial3android;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutorial3android.data.Game;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String PREF_NAME = "selectedGamesPref";
    private static final String KEY_SELECTED_GAMES = "selectedGames";

    private RecyclerView recyclerView;
    private GameNamePriceAdapter adapter;
    private List<Game> selectedGamesList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.buywhatgame);

        adapter = new GameNamePriceAdapter(selectedGamesList, new GameNamePriceAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                deleteSelectedGame(position);
            }
        });

        loadSelectedGames();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        Button fpx = findViewById(R.id.fpx);
        fpx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, fpxActivity.class);
                intent.putExtra("selectedGameNames", getSelectedGameNames());
                intent.putExtra("totalPrice", calculateTotalPrice());
                startActivity(intent);
            }
        });

        Button debit = findViewById(R.id.debit);
        debit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, debit_cardActivity.class);
                intent.putExtra("selectedGameNames", getSelectedGameNames());
                intent.putExtra("totalPrice", calculateTotalPrice());
                startActivity(intent);
            }
        });

        Button payment_tng = findViewById(R.id.payment_tng);
        payment_tng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, tngActivity.class);
                intent.putExtra("selectedGameNames", getSelectedGameNames());
                intent.putExtra("totalPrice", calculateTotalPrice());
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
        Button back = findViewById(R.id.more);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
    }

    // ... (existing code)

    @Override
    protected void onResume() {
        super.onResume();
        updateOnResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveSelectedGamesToSharedPreferences();
    }

    private void updateOnResume() {
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("selectedGameName") && intent.hasExtra("selectedGamePrice")) {
            String selectedGameName = intent.getStringExtra("selectedGameName");
            double selectedGamePrice = intent.getDoubleExtra("selectedGamePrice", 0.0);

            // Add the received game data to the list and update the RecyclerView
            addSelectedGameToRecyclerView(selectedGameName, selectedGamePrice);
        }

        updateTotalPriceTextView();
    }

    private void saveSelectedGamesToSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String selectedGamesJson = convertGameListToJson(selectedGamesList);
        editor.putString(KEY_SELECTED_GAMES, selectedGamesJson);
        editor.apply();
    }

    private ArrayList<String> getSelectedGameNames() {
        ArrayList<String> selectedGameNames = new ArrayList<>();
        for (Game game : selectedGamesList) {
            selectedGameNames.add(game.getGameName());
        }
        return selectedGameNames;
    }

    private void loadSelectedGames() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        String savedGamesJson = sharedPreferences.getString(KEY_SELECTED_GAMES, "");
        if (!savedGamesJson.isEmpty()) {
            List<Game> savedGamesList = convertJsonToGameList(savedGamesJson);
            selectedGamesList.addAll(savedGamesList);
            updateRecyclerView();
        }
    }

    private void updateSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String selectedGamesJson = convertGameListToJson(selectedGamesList);
        editor.putString(KEY_SELECTED_GAMES, selectedGamesJson);
        editor.apply();
    }

    private String convertGameListToJson(List<Game> gameList) {
        JSONArray jsonArray = new JSONArray();
        for (Game game : gameList) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("name", game.getGameName());
                jsonObject.put("price", game.getPrice());
                jsonObject.put("description", game.getDescription());
                jsonArray.put(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jsonArray.toString();
    }

    private List<Game> convertJsonToGameList(String json) {
        List<Game> gameList = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String name = jsonObject.getString("name");
                double price = jsonObject.getDouble("price");
                String description = jsonObject.getString("description");
                Game game = new Game(name, price, description);
                gameList.add(game);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return gameList;
    }

    private void updateRecyclerView() {
        adapter.updateList(selectedGamesList);
    }

    private void deleteSelectedGame(int position) {
        if (position >= 0 && position < selectedGamesList.size()) {
            selectedGamesList.remove(position);
            updateRecyclerView();
            updateSharedPreferences();
        }
    }

    private void addSelectedGameToRecyclerView(String selectedGameName, double selectedGamePrice) {
        if (!isGameAlreadyAdded(selectedGameName)) {
            Game selectedGame = new Game(selectedGameName, selectedGamePrice, "");
            selectedGamesList.add(selectedGame);
            updateRecyclerView();
            updateTotalPriceTextView();
            updateSharedPreferences();
        }
    }

    private boolean isGameAlreadyAdded(String selectedGameName) {
        for (Game game : selectedGamesList) {
            if (game.getGameName().equals(selectedGameName)) {
                return true;
            }
        }
        return false;
    }

    private void updateTotalPriceTextView() {
        double totalPrice = calculateTotalPrice();
        Log.d(TAG, "updateTotalPriceTextView: Total Price to display: " + totalPrice);

        TextView totalTextView = findViewById(R.id.total);
        if (totalTextView != null) {
            totalTextView.setText(String.format("%.2f", totalPrice));
        } else {
            Log.e(TAG, "Total TextView is null");
        }
    }

    private double calculateTotalPrice() {
        double total = 0;
        for (Game game : selectedGamesList) {
            total += game.getPrice();
        }
        return total;
    }
}
