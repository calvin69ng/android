package com.example.tutorial3android;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GenreData implements Parcelable, Serializable {
    private Integer id;
    private String genreName;
    private List<String> genres;

    public GenreData(Integer id, String genreName, List<String> genres) {
        this.id = id;
        this.genreName = genreName;
        this.genres = genres != null ? new ArrayList<>(genres) : new ArrayList<>();
    }

    protected GenreData(Parcel in) {
        id = in.readInt();
        genreName = in.readString();
        genres = in.createStringArrayList();
    }

    public static final Creator<GenreData> CREATOR = new Creator<GenreData>() {
        @Override
        public GenreData createFromParcel(Parcel in) {
            return new GenreData(in);
        }

        @Override
        public GenreData[] newArray(int size) {
            return new GenreData[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(genreName);
        dest.writeStringList(genres);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public static List<String> getGenreNames(List<GenreData> genres) {
        List<String> genreNames = new ArrayList<>();
        for (GenreData genre : genres) {
            genreNames.add(genre.getGenreName());
        }
        return genreNames;
    }

    public static List<GenreData> getSelectedGenres(List<GenreData> allGenres, Set<Integer> selectedPositions) {
        List<GenreData> selectedGenres = new ArrayList<>();
        for (Integer position : selectedPositions) {
            if (position >= 0 && position < allGenres.size()) {
                selectedGenres.add(allGenres.get(position));
            }
        }
        return selectedGenres;
    }
}
