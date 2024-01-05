package com.example.tutorial3android;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameManager {

    private SQLiteDatabase database;
    private Context context;

    private static final String TABLE_NAME_GAMES = "Games";
    private static final String COL_ID = "_id";
    private static final String COL_NAME = "name";
    private static final String COL_PRICE = "price";
    private static final String COL_DESCRIPTION = "description";
    private static final String COL_GENRE = "genre";

    public GameManager(Context context) {
        this.context = context;
        database = context.openOrCreateDatabase("GameDatabase", Context.MODE_PRIVATE, null);

        // Create the Games table if not exists
        database.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME_GAMES + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NAME + " TEXT, " +
                COL_PRICE + " REAL, " +
                COL_DESCRIPTION + " TEXT, " +
                COL_GENRE + " TEXT);");
    }

    public void deleteAllGames() {
        database.delete(TABLE_NAME_GAMES, null, null);
    }

    public long insertGame(game_data gameData) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME, gameData.getName());
        contentValues.put(COL_PRICE, gameData.getPrice());
        contentValues.put(COL_DESCRIPTION, gameData.getDescription());

        // Assuming getGenres() returns a list of genres
        List<String> genres = gameData.getGenres();
        String genreString = "";
        if (genres != null && !genres.isEmpty()) {
            genreString = TextUtils.join(",", genres);
        }
        contentValues.put(COL_GENRE, genreString);

        return database.insert(TABLE_NAME_GAMES, null, contentValues);
    }

    public List<game_data> getAllGames() {
        List<game_data> games = new ArrayList<>();
        Cursor cursor = database.query(TABLE_NAME_GAMES, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(COL_ID));
                String name = cursor.getString(cursor.getColumnIndex(COL_NAME));
                double price = cursor.getDouble(cursor.getColumnIndex(COL_PRICE));
                String description = cursor.getString(cursor.getColumnIndex(COL_DESCRIPTION));
                String genreString = cursor.getString(cursor.getColumnIndex(COL_GENRE));

                // Convert the genreString into a list of genres
                List<String> genres = Arrays.asList(TextUtils.split(genreString, ","));

                game_data gameData = new game_data(id, name, price, description, genres);
                games.add(gameData);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return games;
    }

    public List<String> getAllGameNames() {
        List<String> gameNames = new ArrayList<>();
        Cursor cursor = database.query(TABLE_NAME_GAMES, new String[]{COL_NAME}, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex(COL_NAME));
                gameNames.add(name);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return gameNames;
    }

    public game_data getGameByName(String gameName) {
        if (gameName == null) {
            // Handle the case where gameName is null, e.g., show an error message
            showToast("Invalid game name");
            return null;
        }

        String selection = COL_NAME + "=?";
        String[] selectionArgs = {gameName};
        Cursor cursor = database.query(TABLE_NAME_GAMES, null, selection, selectionArgs, null, null, null);

        game_data gameData = null;
        if (cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndex(COL_ID));
            String name = cursor.getString(cursor.getColumnIndex(COL_NAME));
            double price = cursor.getDouble(cursor.getColumnIndex(COL_PRICE));
            String description = cursor.getString(cursor.getColumnIndex(COL_DESCRIPTION));
            String genreString = cursor.getString(cursor.getColumnIndex(COL_GENRE));

            // Convert the genreString into a list of genres
            List<String> genres = Arrays.asList(TextUtils.split(genreString, ","));

            gameData = new game_data(id, name, price, description, genres);
        }

        cursor.close();
        return gameData;
    }

    public boolean deleteGameById(int gameId) {
        String whereClause = COL_ID + "=?";
        String[] whereArgs = {String.valueOf(gameId)};

        // Delete the game and get the number of affected rows
        int rowsDeleted = database.delete(TABLE_NAME_GAMES, whereClause, whereArgs);

        // Check if any row was deleted
        return rowsDeleted > 0;
    }

    public boolean deleteGameByName(String gameName) {
        // Check if the gameName is null or empty
        if (TextUtils.isEmpty(gameName)) {
            // Handle the case where gameName is empty, e.g., show an error message
            showToast("Invalid game name");
            return false;
        }

        String whereClause = COL_NAME + "=?";
        String[] whereArgs = {gameName};

        // Delete the game and get the number of affected rows
        int rowsDeleted = database.delete(TABLE_NAME_GAMES, whereClause, whereArgs);

        // Check if any row was deleted
        return rowsDeleted > 0;
    }

    public void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
