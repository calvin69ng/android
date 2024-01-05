package com.example.tutorial3android;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GenreManager {

    private SQLiteDatabase database;
    private Context context;
    private GenreHelper genreHelper;

    private static final String TABLE_NAME_GENRES = "Genres";
    private static final String COL_ID_GENRE = "_id";
    private static final String COL_GENRE_NAME = "genreName";

    public GenreManager(Context context) {
        this.context = context;
        this.genreHelper = new GenreHelper(context);
    }

    public GenreManager open() throws SQLException {
        database = genreHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        genreHelper.close();
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

    public long insertGenre(GenreData genreData) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_GENRE_NAME, genreData.getGenreName());
        return database.insert(TABLE_NAME_GENRES, null, contentValues);
    }

    public Cursor getAllGenres() {
        String[] columns = new String[]{COL_ID_GENRE, COL_GENRE_NAME};
        return database.query(TABLE_NAME_GENRES, columns, null, null, null, null, null);
    }

    public List<GenreData> getAllGenresList() {
        List<GenreData> genres = new ArrayList<>();
        Cursor cursor = getAllGenres();

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(COL_ID_GENRE));
                String name = cursor.getString(cursor.getColumnIndex(COL_GENRE_NAME));

                GenreData genreData = new GenreData(id, name, null);  // Assuming genres can be null
                genres.add(genreData);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return genres;
    }

    public boolean deleteGenreByName(String genreName) {
        // Define the where clause
        String selection = COL_GENRE_NAME + " = ?";

        // Specify the arguments in the where clause
        String[] selectionArgs = {genreName};

        // Perform the deletion
        int deletedRows = database.delete(TABLE_NAME_GENRES, selection, selectionArgs);

        // Return true if at least one row is deleted, indicating success
        return deletedRows > 0;
    }

    public void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
