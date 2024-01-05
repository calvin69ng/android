package com.example.tutorial3android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class tngActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tng);


        Button tng_payment = findViewById(R.id.tng_payment);
        tng_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(tngActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        Button tng_payment_success = findViewById(R.id.tng_payment_success);
        tng_payment_success.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(tngActivity.this, payment_successActivity.class);
                startActivity(intent);
            }
        });

    }
}