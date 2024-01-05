package com.example.tutorial3android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class fpxActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fpx);


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
                Intent intent = new Intent(fpxActivity.this, payment_successActivity.class);
                startActivity(intent);
            }
        });
    }
}