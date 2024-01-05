package com.example.tutorial3android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class editgame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editgame);

        Button add_game = findViewById(R.id.button50);
        add_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(editgame.this, AddGameActivity.class);
                startActivity(intent);
            }
        });

        Button change_game = findViewById(R.id.button2);
        change_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(editgame.this, game_editlist_Activity.class);
                startActivity(intent);
            }
        });

        Button back = findViewById(R.id.button5);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(editgame.this, adminpage.class);
                startActivity(intent);
            }
        });
    }
}