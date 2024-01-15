package com.example.tutorial3android;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.tutorial3android.data.Genre;

import java.util.List;
import java.util.Set;

public class GenreDataAdapter extends ArrayAdapter<Genre> {

    private Context context;
    private List<Genre> genreList;
    private Set<Genre> selectedGenres;
    private List<Genre> preSelectedGenres;

    public GenreDataAdapter(Context context, List<Genre> genreList, Set<Genre> selectedGenres, List<Genre> preSelectedGenres) {
        super(context, 0, genreList);
        this.context = context;
        this.genreList = genreList;
        this.selectedGenres = selectedGenres;
        this.preSelectedGenres = preSelectedGenres;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(context).inflate(R.layout.list_item_genre_layout, parent, false);
        }

        Genre currentGenre = genreList.get(position);

        TextView genreName = listItem.findViewById(R.id.messageTextView);
        genreName.setText(currentGenre.getGenreName());

        // Check if the current genre is pre-selected
        if (preSelectedGenres != null && preSelectedGenres.contains(currentGenre)) {
            // Change the background color to indicate pre-selection
            listItem.setBackgroundColor(ContextCompat.getColor(context, R.color.selected_color));
        } else if (selectedGenres.contains(currentGenre)) {
            // Change the background color when selected
            listItem.setBackgroundColor(ContextCompat.getColor(context, R.color.selected_color));
        } else {
            // Set the default background color
            listItem.setBackgroundColor(Color.TRANSPARENT);
        }

        return listItem;
    }

    public void updateData(Set<Genre> selectedGenres) {
        this.selectedGenres = selectedGenres;
        notifyDataSetChanged();
    }
}
