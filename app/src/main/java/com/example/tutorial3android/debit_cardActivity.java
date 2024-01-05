package com.example.tutorial3android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class debit_cardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debit_card);


        Button debit_back = findViewById(R.id.debit_back);
        debit_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(debit_cardActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        Button debit_success = findViewById(R.id.debit_success);
        debit_success.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(debit_cardActivity.this, payment_successActivity.class);
                startActivity(intent);
            }
        });
    }
}