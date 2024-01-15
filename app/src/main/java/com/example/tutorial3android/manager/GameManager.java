package com.example.tutorial3android.manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tutorial3android.data.Game;
import com.example.tutorial3android.helper.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class GameManager {
    private DatabaseHelper dbHelper;
    private SQLiteDatabase database; // Declare database variable

    public GameManager(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public long insertGame(Game game) {
        openDatabase(); // Open the database before performing operations
        ContentValues values = new ContentValues();
        values.put("game_name", game.getGameName());
        values.put("price", game.getPrice());
        values.put("description", game.getDescription());

        long newRowId = database.insert("Game", null, values);
        closeDatabase(); // Close the database after performing operations
        return newRowId;
    }

    public List<String> getAllGameNames() {
        openDatabase();
        List<String> gameNames = new ArrayList<>();

        try (Cursor cursor = database.query(
                "Game",
                new String[]{"game_name"},
                null,
                null,
                null,
                null,
                null
        )) {
            while (cursor.moveToNext()) {
                gameNames.add(cursor.getString(cursor.getColumnIndex("game_name")));
            }
        } finally {
            closeDatabase();
        }

        return gameNames;
    }

    public Game getGameByName(String gameName) {
        openDatabase(); // Open the database before performing operations

        String[] projection = {"game_name", "price", "description"};
        String selection = "game_name = ?";
        String[] selectionArgs = {gameName};

        try (Cursor cursor = database.query(
                "Game",
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        )) {
            if (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex("game_name"));
                double price = cursor.getDouble(cursor.getColumnIndex("price"));
                String description = cursor.getString(cursor.getColumnIndex("description"));

                return new Game(name, price, description);
            }
        } finally {
            closeDatabase(); // Close the database after performing operations
        }

        return null; // Return null if the game is not found
    }

    public long updateGame(String gameName, Game updatedGameData) {
        openDatabase(); // Open the database before performing operations

        ContentValues values = new ContentValues();
        values.put("game_name", updatedGameData.getGameName());
        values.put("price", updatedGameData.getPrice());
        values.put("description", updatedGameData.getDescription());

        String selection = "game_name = ?";
        String[] selectionArgs = {gameName};

        long updatedRows = database.update("Game", values, selection, selectionArgs);

        closeDatabase(); // Close the database after performing operations
        return updatedRows;
    }

    public boolean deleteGameAndGenres(String gameName) {
        openDatabase(); // Open the database before performing operations

        try {
            // Step 1: Delete game-genre entries associated with the game name
            String deleteGenreQuery = "DELETE FROM GameGenre WHERE game_name = ?";
            database.execSQL(deleteGenreQuery, new String[]{gameName});

            // Step 2: Delete the game entry
            String deleteGameQuery = "DELETE FROM Game WHERE game_name = ?";
            database.execSQL(deleteGameQuery, new String[]{gameName});

            return true;
        } catch (Exception e) {
            e.printStackTrace(); // Log the error for debugging
            // Handle the error as needed, such as showing a toast or returning a specific error code
            return false;
        } finally {
            closeDatabase(); // Close the database after performing operations
        }
    }

    private void openDatabase() {
        database = dbHelper.getWritableDatabase();
    }

    private void closeDatabase() {
        if (database != null && database.isOpen()) {
            database.close();
        }
    }

    public boolean isGameExist(String gameName) {
        openDatabase(); // Open the database before performing operations

        String[] projection = {"game_name"};
        String selection = "game_name = ?";
        String[] selectionArgs = {gameName};

        // Use try-with-resources to ensure the cursor is properly closed
        try (Cursor cursor = database.query(
                "Game",
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        )) {
            return cursor.getCount() > 0;
        } finally {
            closeDatabase(); // Close the database after performing operations
        }
    }

    // Add methods specific to Game entity
    // ...
}
