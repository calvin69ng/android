package com.example.tutorial3android;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class fpxActivity extends AppCompatActivity {
    private static final String TAG = "FpxActivity"; // Define a tag for your logs

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fpx);

        ArrayList<String> selectedGameNames = getIntent().getStringArrayListExtra("selectedGameNames");
        double totalPrice = getIntent().getDoubleExtra("totalPrice", 0.0);

        SharedPreferences preferences = getSharedPreferences("user_info", MODE_PRIVATE);
        String username = preferences.getString("username", "");

        // Display total in TextView
        TextView totalTextView = findViewById(R.id.total2);
        totalTextView.setText(String.format("%.2f", totalPrice));

        Button fpx_back = findViewById(R.id.fpx_back);
        fpx_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(fpxActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button fpx_success = findViewById(R.id.fpx_success);
        fpx_success.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handlePayment(selectedGameNames, totalPrice, username);
            }
        });
    }

    private UserData getUserData(String username) {
        // Implement this method to retrieve UserData from your data source
        // (e.g., database, shared preferences, etc.)

        user_dbmanager dbManager = new user_dbmanager(fpxActivity.this);  // Using the activity's context


        // Assuming you have a method to get UserData by username from your database manager
        UserData userData = dbManager.getUserByUsername(username);

        dbManager.close();

        return userData;
    }

    private void updateUserData(UserData userData) {
        // Implement this method to update UserData in your data source
        // (e.g., database, shared preferences, etc.)

        user_dbmanager dbManager = new user_dbmanager(fpxActivity.this);  // Using the activity's context


        // Assuming you have a method to update UserData in your database manager
        dbManager.updateUserData(userData);

        dbManager.close();
    }

    private void handlePayment(List<String> selectedGameNames, double totalPrice, String username) {
        // Create user-game associations and store them
        UserGameDAO userGameDAO = new UserGameDAO(fpxActivity.this);
        for (String gameName : selectedGameNames) {
            userGameDAO.addUserGameAssociation(username, gameName);
        }
        userGameDAO.closeDatabase();

        // Concatenate the game names into a single string
        StringBuilder boughtGamesMessage = new StringBuilder("Bought ");
        for (int i = 0; i < selectedGameNames.size(); i++) {
            boughtGamesMessage.append(selectedGameNames.get(i));
            if (i < selectedGameNames.size() - 1) {
                boughtGamesMessage.append(", ");
            }
        }

        // Create NotificationData
        String receiver = username + " ";  // Concatenate the username
        NotificationData notificationData = new NotificationData(
                System.currentTimeMillis(),
                "system",
                boughtGamesMessage.toString(),
                new Date(),
                receiver
        );

        // Log the NotificationData
        Log.d(TAG, "NotificationData: " + notificationData.toString());

        // Create IncomeData
        IncomeData incomeData = new IncomeData(username, totalPrice, new Date(), selectedGameNames);

        // Log the IncomeData
        Log.d(TAG, "IncomeData: " + incomeData.toString());

        // Save notificationData and incomeData to wherever needed
        // For example, you can store them in a database or use them in your app as required

        // Navigate to payment_successActivity
        Intent intent = new Intent(fpxActivity.this, payment_successActivity.class);
        startActivity(intent);
    }
}
