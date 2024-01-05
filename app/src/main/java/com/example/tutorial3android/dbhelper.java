package com.example.tutorial3android;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dbhelper extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "UserList";

    // Table columns
    public static final String _ID = "_id";
    public static final String Gmail = "gmail";
    public static final String Username = "username";

    public static final String Password = "password";


    // Database Information
    static final String DB_NAME = "JOURNALDEV_UserList1.DB";

    // database version
    static final int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Gmail + " TEXT NOT NULL, " + Username + " TEXT, " + Password + " TEXT);";


    public dbhelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Method to check if the entered credentials match a user in the SQLite database
    public boolean  checkUserCredentials(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {Username, Password};
        String selection = Username + "=? AND " + Password + "=?";
        String[] selectionArgs = {username, password};

        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);

        boolean isValid = cursor.moveToFirst(); // If moveToFirst() returns true, it means the user exists

        cursor.close();
        db.close();

        return isValid;
    }}