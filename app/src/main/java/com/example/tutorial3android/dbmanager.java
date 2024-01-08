package com.example.tutorial3android;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class dbmanager {

    private dbhelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public dbmanager(Context c) {
        context = c;
    }

    public dbmanager open() throws SQLException {
        dbHelper = new dbhelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String gmail, String username, String password) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(dbhelper.Gmail, gmail);
        contentValue.put(dbhelper.Username, username);
        contentValue.put(dbhelper.Password, password);
        database.insert(dbhelper.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[]{dbhelper._ID, dbhelper.Gmail, dbhelper.Username, dbhelper.Password};
        Cursor cursor = database.query(dbhelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id, String Gmail, String username, String password) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbhelper.Gmail, Gmail);
        contentValues.put(dbhelper.Username, username);
        contentValues.put(dbhelper.Password, password);
        int i = database.update(dbhelper.TABLE_NAME, contentValues, dbhelper._ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(dbhelper.TABLE_NAME, dbhelper._ID + "=" + _id, null);
    }

    public UserData getUserById(int userId) {
        String[] columns = new String[]{dbhelper._ID, dbhelper.Gmail, dbhelper.Username, dbhelper.Password};

        Cursor cursor = database.query(dbhelper.TABLE_NAME, columns, dbhelper._ID + " = " + userId, null, null, null, null);

        UserData user = null;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int _id = cursor.getInt(cursor.getColumnIndex(dbhelper._ID));
                String gmail = cursor.getString(cursor.getColumnIndex(dbhelper.Gmail));
                String username = cursor.getString(cursor.getColumnIndex(dbhelper.Username));
                String password = cursor.getString(cursor.getColumnIndex(dbhelper.Password));

                user = new UserData(_id, gmail, username, password);
            }

            cursor.close();
        }

        return user;
    }

    public UserData getUserByUsername(String username) {
        String[] columns = new String[]{dbhelper._ID, dbhelper.Gmail, dbhelper.Username, dbhelper.Password};

        Cursor cursor = database.query(
                dbhelper.TABLE_NAME,
                columns,
                dbhelper.Username + " = ?",
                new String[]{username},
                null,
                null,
                null
        );

        UserData user = null;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int _id = cursor.getInt(cursor.getColumnIndex(dbhelper._ID));
                String gmail = cursor.getString(cursor.getColumnIndex(dbhelper.Gmail));
                String fetchedUsername = cursor.getString(cursor.getColumnIndex(dbhelper.Username));
                String password = cursor.getString(cursor.getColumnIndex(dbhelper.Password));

                if (fetchedUsername.equals(username)) {
                    user = new UserData(_id, gmail, fetchedUsername, password);
                }
            }

            cursor.close();
        }

        return user;
    }

    public void updateUserData(UserData userData) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbhelper.Gmail, userData.getGmail());
        contentValues.put(dbhelper.Username, userData.getUsername());
        contentValues.put(dbhelper.Password, userData.getPassword());

        database.update(
                dbhelper.TABLE_NAME,
                contentValues,
                dbhelper.Username + " = ?",
                new String[]{userData.getUsername()}
        );
    }
}