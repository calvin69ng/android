package com.example.tutorial3android;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import java.sql.SQLException; // Add this import
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.Date; // Add this import
import java.util.List;

public class NotificationDAO {

    private SQLiteDatabase database;
    private NotificationDbHelper dbHelper;

    public NotificationDAO(Context context) {
        dbHelper = new NotificationDbHelper(context);
    }

    public void openDatabase() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void closeDatabase() {
        dbHelper.close();
    }

    public long addNotification(NotificationData notification) {
        ContentValues values = new ContentValues();
        values.put(NotificationDbHelper.COLUMN_SENDER, notification.getSender());
        values.put(NotificationDbHelper.COLUMN_MESSAGE, notification.getMessage());
        values.put(NotificationDbHelper.COLUMN_DATE, notification.getDate().getTime());
        values.put(NotificationDbHelper.COLUMN_RECEIVER, notification.getReceiver());

        return database.insert(NotificationDbHelper.TABLE_NOTIFICATION, null, values);
    }

    public List<NotificationData> getNotificationsForUser(String username) {
        List<NotificationData> notificationList = new ArrayList<>();
        String[] columns = {
                NotificationDbHelper.COLUMN_ID,
                NotificationDbHelper.COLUMN_SENDER,
                NotificationDbHelper.COLUMN_MESSAGE,
                NotificationDbHelper.COLUMN_DATE,
                NotificationDbHelper.COLUMN_RECEIVER
        };
        String selection = NotificationDbHelper.COLUMN_RECEIVER + " = ?";
        String[] selectionArgs = {username};
        String orderBy = NotificationDbHelper.COLUMN_DATE + " DESC";

        Cursor cursor = database.query(
                NotificationDbHelper.TABLE_NOTIFICATION,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                orderBy
        );

        if (cursor != null && cursor.moveToFirst()) {
            do {
                long id = cursor.getLong(cursor.getColumnIndex(NotificationDbHelper.COLUMN_ID));
                String sender = cursor.getString(cursor.getColumnIndex(NotificationDbHelper.COLUMN_SENDER));
                String message = cursor.getString(cursor.getColumnIndex(NotificationDbHelper.COLUMN_MESSAGE));
                long dateMillis = cursor.getLong(cursor.getColumnIndex(NotificationDbHelper.COLUMN_DATE));
                Date date = new Date(dateMillis);
                String receiver = cursor.getString(cursor.getColumnIndex(NotificationDbHelper.COLUMN_RECEIVER));

                NotificationData notification = new NotificationData(id, sender, message, date, receiver);
                notificationList.add(notification);
            } while (cursor.moveToNext());

            cursor.close();
        }

        return notificationList;
    }
}