package com.example.tutorial3android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button fpx = findViewById(R.id.fpx);
        fpx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, fpxActivity.class);
                startActivity(intent);
            }
        });

        Button debit = findViewById(R.id.debit);
        debit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, debit_cardActivity.class);
                startActivity(intent);
            }
        });

        Button payment_menu = findViewById(R.id.payment_menu);
        payment_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, usermenuActivity.class);
                startActivity(intent);
            }
        });

        Button payment_tng = findViewById(R.id.payment_tng);
        payment_tng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, usermenuActivity.class);
                startActivity(intent);
            }
        });
    }
}
