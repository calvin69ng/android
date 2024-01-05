package com.example.tutorial3android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class adminpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminpage);


        Button edit_game = findViewById(R.id.button50);
        Button edit_genre = findViewById(R.id.button3);
        Button edit_account = findViewById(R.id.button4);
        Button userpage = findViewById(R.id.button5);


        edit_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(adminpage.this, editgame.class);
                startActivity(intent);
            }
        });

        userpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(adminpage.this, usermenuActivity.class);
                startActivity(intent);
            }
        });

        edit_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(adminpage.this, accountActivity.class);
                startActivity(intent);
            }
        });

        edit_genre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(adminpage.this, addgenreActivity.class);
                startActivity(intent);
            }
        });


    }
}