package com.example.tutorial3android.manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.tutorial3android.data.Income;
import com.example.tutorial3android.helper.DatabaseHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class IncomeManager {
    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;
    private static final String TAG = "IncomeManager";

    public IncomeManager(Context context) {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    public long insertIncome(Income income) {
        ContentValues values = new ContentValues();
        values.put("username", income.getUsername());
        values.put("total", income.getTotal());
        values.put("message", income.getMessage());
        values.put("time", income.getTime());

        long newRowId = database.insert("Income", null, values);
        return newRowId;
    }

    public List<Income> getAllIncome() {
        List<Income> incomeList = new ArrayList<>();

        try (Cursor cursor = database.query("Income", null, null, null, null, null, null)) {
            while (cursor.moveToNext()) {
                String username = cursor.getString(cursor.getColumnIndexOrThrow("username"));
                double total = cursor.getDouble(cursor.getColumnIndexOrThrow("total"));
                String message = cursor.getString(cursor.getColumnIndexOrThrow("message"));
                String time = cursor.getString(cursor.getColumnIndexOrThrow("time"));

                Income income = new Income(username, total, message, time);
                incomeList.add(income);
            }
        } catch (Exception e) {
            Log.e(TAG, "Error getting all income: " + e.getMessage());
        }

        return incomeList;
    }


    // Add methods specific to Income entity
    // ...
}
