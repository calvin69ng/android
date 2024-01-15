package com.example.tutorial3android.manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tutorial3android.data.Genre;
import com.example.tutorial3android.helper.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class GenreManager {
    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;  // Add this line

    // Constants for table name and column name
    private static final String TABLE_NAME_GENRES = "Genre";
    public static final String COL_GENRE_NAME = "genre_name";


    public GenreManager(Context context) {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();  // Add this line
    }

    public long insertGenre(Genre genre) {
        ContentValues values = new ContentValues();
        values.put(COL_GENRE_NAME, genre.getGenreName());

        long newRowId = database.insert(TABLE_NAME_GENRES, null, values);
        return newRowId;
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public boolean isGenreExists(String genreName) {
        Cursor cursor = null;

        try {
            String query = "SELECT * FROM " + TABLE_NAME_GENRES + " WHERE " +
                    COL_GENRE_NAME + " = ?";
            cursor = database.rawQuery(query, new String[]{genreName});

            return cursor.getCount() > 0;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public boolean deleteGenreByName(String genreName) {
        String selection = COL_GENRE_NAME + " = ?";
        String[] selectionArgs = {genreName};
        int deletedRows = database.delete(TABLE_NAME_GENRES, selection, selectionArgs);
        return deletedRows > 0;
    }

    public List<Genre> getAllGenresList() {
        List<Genre> genres = new ArrayList<>();

        try (Cursor cursor = database.query(TABLE_NAME_GENRES, new String[]{COL_GENRE_NAME},
                null, null, null, null, null)) {
            while (cursor.moveToNext()) {
                String genreName = cursor.getString(cursor.getColumnIndex(COL_GENRE_NAME));
                genres.add(new Genre(genreName));
            }
        }

        return genres;
    }

    public Cursor getAllGenres() {
        return database.query(TABLE_NAME_GENRES, new String[]{COL_GENRE_NAME},
                null, null, null, null, null);
    }

    // Add methods specific to Genre entity
    // ...
}