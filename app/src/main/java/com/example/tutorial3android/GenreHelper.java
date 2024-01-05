package com.example.tutorial3android;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class GenreHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "YourDatabaseName";
    private static final int DATABASE_VERSION = 2;  // Incremented the version

    // Table and column names
    public static final String TABLE_GENRES = "Genres";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_GENRE_NAME = "genreName";

    // New table for games
    public static final String TABLE_GAMES = "Games";
    public static final String COLUMN_GAME_ID = "_gameId";
    public static final String COLUMN_GAME_NAME = "gameName";

    // New table for the relationship between games and genres
    public static final String TABLE_GAME_GENRE = "GameGenre";
    public static final String COLUMN_GAME_GENRE_ID = "_id";
    public static final String COLUMN_GAME_GENRE_GAME_ID = "_gameId";
    public static final String COLUMN_GAME_GENRE_GENRE_ID = "_genreId";

    // Database creation SQL statements
    private static final String DATABASE_CREATE_GENRES = "create table "
            + TABLE_GENRES + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_GENRE_NAME
            + " text not null);";

    private static final String DATABASE_CREATE_GAMES = "create table "
            + TABLE_GAMES + "(" + COLUMN_GAME_ID
            + " integer primary key autoincrement, " + COLUMN_GAME_NAME
            + " text not null);";

    private static final String DATABASE_CREATE_GAME_GENRE = "create table "
            + TABLE_GAME_GENRE + "(" + COLUMN_GAME_GENRE_ID
            + " integer primary key autoincrement, " + COLUMN_GAME_GENRE_GAME_ID
            + " integer, " + COLUMN_GAME_GENRE_GENRE_ID + " integer);";

    private Context context;

    public GenreHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        // Create tables
        database.execSQL(DATABASE_CREATE_GENRES);
        database.execSQL(DATABASE_CREATE_GAMES);
        database.execSQL(DATABASE_CREATE_GAME_GENRE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrades, if needed
        if (oldVersion < 2) {
            // If upgrading from version 1 to version 2
            db.execSQL(DATABASE_CREATE_GAMES);
            db.execSQL(DATABASE_CREATE_GAME_GENRE);
        }
        // You can add more conditions for other versions if necessary
    }

    public GenreData getGenreData(String genreName) {
        GenreData genreData = null;
        SQLiteDatabase database = getWritableDatabase();
        String[] columns = new String[]{COLUMN_ID, COLUMN_GENRE_NAME};
        String selection = COLUMN_GENRE_NAME + "=?";
        String[] selectionArgs = new String[]{genreName};

        Cursor cursor = database.query(TABLE_GENRES, columns, selection, selectionArgs, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(COLUMN_ID);
            int nameIndex = cursor.getColumnIndex(COLUMN_GENRE_NAME);

            int id = cursor.getInt(idIndex);
            String name = cursor.getString(nameIndex);

            genreData = new GenreData(id, name, null);  // Assuming genres can be null
        }

        if (cursor != null) {
            cursor.close();
        }

        return genreData;
    }

    public void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public void clearFields() {
        // Implement this method to clear any relevant fields if needed.
        // For example, if you have EditText fields in your activity, clear them here.
    }
}
