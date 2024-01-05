package com.example.tutorial3android;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class game_data implements Parcelable {
    private Integer _id;
    private String name;
    private double price;
    private String description;
    private List<String> genres;

    public game_data(Integer _id, String name, double price, String description, List<String> genres) {
        this._id = _id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.genres = genres;
    }

    protected game_data(Parcel in) {
        if (in.readByte() == 0) {
            _id = null;
        } else {
            _id = in.readInt();
        }
        name = in.readString();
        price = in.readDouble();
        description = in.readString();
        genres = in.createStringArrayList();
    }

    public static final Creator<game_data> CREATOR = new Creator<game_data>() {
        @Override
        public game_data createFromParcel(Parcel in) {
            return new game_data(in);
        }

        @Override
        public game_data[] newArray(int size) {
            return new game_data[size];
        }
    };

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getGenres() {
        return genres;
    }

    public Integer get_id() {
        return _id;
    }

    // Add methods to update or modify the attributes if needed
    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    // Helper method to get a formatted string of genres
    public String getFormattedGenres() {
        if (genres != null && !genres.isEmpty()) {
            return String.join(", ", genres);
        } else {
            return "";
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (_id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(_id);
        }
        dest.writeString(name);
        dest.writeDouble(price);
        dest.writeString(description);
        dest.writeStringList(genres);
    }
}
