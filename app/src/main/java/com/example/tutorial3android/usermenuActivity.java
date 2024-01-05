package com.example.tutorial3android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class usermenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usermenu);

        Intent intent = getIntent();
        boolean isAdmin = intent.getBooleanExtra("isAdmin", false);
        Button btn_addgame = findViewById(R.id.btn_addgame);

        if (isAdmin) {
            btn_addgame.setVisibility(View.VISIBLE);
        } else {
            btn_addgame.setVisibility(View.GONE);
        }

        Button usermenu_search = findViewById(R.id.usermenu_search);
        usermenu_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(usermenuActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

        btn_addgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(usermenuActivity.this, adminpage.class);
                startActivity(intent);
            }
        });
    }
}