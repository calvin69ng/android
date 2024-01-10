package com.example.tutorial3android;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        SharedPreferences preferences = getSharedPreferences("user_info", MODE_PRIVATE);
        String username = preferences.getString("username", "");

        RecyclerView recyclerView = findViewById(R.id.notification1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<NotificationData> notificationDataList = new ArrayList<>();
        // Populate the notificationDataList with your data

        NotificationAdapter adapter = new NotificationAdapter(notificationDataList);
        recyclerView.setAdapter(adapter);

        Button back = findViewById(R.id.button6);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotificationActivity.this, usermenuActivity.class);
                startActivity(intent);
            }
        });
    }
}