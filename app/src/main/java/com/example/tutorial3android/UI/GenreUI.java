package com.example.tutorial3android.UI;

import android.content.Context;
import android.database.Cursor;

import com.example.tutorial3android.data.Genre;
import com.example.tutorial3android.manager.GenreManager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GenreUI {
    private Context context;
    private GenreManager genreManager;

    public GenreUI(Context context) {
        this.context = context;
        genreManager = new GenreManager(context);
    }

    // UI-related methods
    // UI-related methods
    public List<Genre> getAllGenresList() {
        List<Genre> genres = new ArrayList<>();

        // Assuming GenreManager has a method to fetch all genres from the database
        Cursor cursor = genreManager.getAllGenres();

        try {
            while (cursor.moveToNext()) {
                String genreName = cursor.getString(cursor.getColumnIndex(GenreManager.COL_GENRE_NAME));
                genres.add(new Genre(genreName));
            }
        } finally {
            cursor.close();
        }

        return genres;
    }

    public Set<Genre> getSelectedGenresSet(List<Genre> selectedGenres) {
        // Convert the list of selected genres to a set
        return new HashSet<>(selectedGenres);
    }
}