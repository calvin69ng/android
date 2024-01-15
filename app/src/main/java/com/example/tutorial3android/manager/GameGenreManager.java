package com.example.tutorial3android.manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tutorial3android.data.GameGenre;
import com.example.tutorial3android.helper.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class GameGenreManager {
    private DatabaseHelper dbHelper;

    public GameGenreManager(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public boolean insertGameGenre(GameGenre gameGenre) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("game_name", gameGenre.getGameName());
        values.put("genre_name", gameGenre.getGenreName());

        try {
            long newRowId = db.insertWithOnConflict(
                    "GameGenre",
                    null,
                    values,
                    SQLiteDatabase.CONFLICT_IGNORE);

            // Check if the insertion was successful (newRowId != -1) or if the record already exists
            return newRowId != -1;
        } catch (Exception e) {
            e.printStackTrace(); // Log the error for debugging
            // Handle the error as needed, such as showing a toast or returning a specific error code
            return false;
        } finally {
            db.close();
        }
    }

    public List<String> getGameNamesByGenre(String genreName) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<String> gameNames = new ArrayList<>();

        try (Cursor cursor = db.query(
                "GameGenre",
                new String[]{"game_name"},
                "genre_name = ?",
                new String[]{genreName},
                null,
                null,
                null
        )) {
            while (cursor.moveToNext()) {
                gameNames.add(cursor.getString(cursor.getColumnIndex("game_name")));
            }
        } finally {
            db.close();
        }

        return gameNames;
    }

    public String getGenreByGameName(String gameName) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String genreName = null;

        try (Cursor cursor = db.query(
                "GameGenre",
                new String[]{"genre_name"},
                "game_name = ?",
                new String[]{gameName},
                null,
                null,
                null
        )) {
            if (cursor.moveToFirst()) {
                genreName = cursor.getString(cursor.getColumnIndex("genre_name"));
            }
        } finally {
            db.close();
        }

        return genreName;
    }

    public int deleteGenresByGameName(String gameName) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            return db.delete("GameGenre", "game_name = ?", new String[]{gameName});
        } catch (Exception e) {
            e.printStackTrace(); // Log the error for debugging
            // Handle the error as needed, such as showing a toast or returning a specific error code
            return -1;
        } finally {
            db.close();
        }
    }

    // Add methods specific to GameGenre entity
    // ...
}
