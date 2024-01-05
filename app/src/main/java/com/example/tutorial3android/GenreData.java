package com.example.tutorial3android;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GenreData {
    private Integer id;
    private String genreName;
    private List<String> genres;

    public GenreData(Integer id, String genreName, List<String> genres) {
        this.id = id;
        this.genreName = genreName;
        this.genres = genres != null ? new ArrayList<>(genres) : new ArrayList<>();
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