package com.example.tutorial3android.manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.tutorial3android.data.Report;
import com.example.tutorial3android.helper.DatabaseHelper;

public class ReportManager {
    private DatabaseHelper dbHelper;

    public ReportManager(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public long insertReport(Report report) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", report.getUsername());
        values.put("title", report.getTitle());
        values.put("comment", report.getComment());

        long newRowId = db.insert("Report", null, values);
        db.close();
        return newRowId;
    }

    // Add methods specific to Report entity
    // ...
}
