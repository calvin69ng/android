package com.example.tutorial3android;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.tutorial3android.data.Genre;

import java.util.List;

public class ClickGenreAdapter extends ArrayAdapter<Genre> {

    private Context context;
    private List<Genre> genreList;

    public ClickGenreAdapter(Context context, List<Genre> genreList) {
        super(context, 0, genreList);
        this.context = context;
        this.genreList = genreList;
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

        // You can customize the appearance based on your requirements
        listItem.setBackgroundColor(Color.TRANSPARENT);

        return listItem;
    }
}