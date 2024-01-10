package com.example.tutorial3android;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NotificationDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "notification.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NOTIFICATION = "notification";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_SENDER = "sender";
    public static final String COLUMN_MESSAGE = "message";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_RECEIVER = "receiver";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NOTIFICATION + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_SENDER + " TEXT, " +
                    COLUMN_MESSAGE + " TEXT, " +
                    COLUMN_DATE + " INTEGER, " +
                    COLUMN_RECEIVER + " TEXT);";

    public NotificationDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTIFICATION);
        onCreate(db);
    }
}
