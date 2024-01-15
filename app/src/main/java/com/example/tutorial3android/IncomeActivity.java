package com.example.tutorial3android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tutorial3android.data.Income;
import com.example.tutorial3android.manager.IncomeManager;

import java.util.List;

public class IncomeActivity extends AppCompatActivity {

    private RecyclerView incomeRecyclerView;
    private IncomeAdapter incomeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);

        incomeRecyclerView = findViewById(R.id.incomelist);
        incomeAdapter = new IncomeAdapter();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        incomeRecyclerView.setLayoutManager(layoutManager);
        incomeRecyclerView.setAdapter(incomeAdapter);

        // Load and display income data
        loadIncomeData();

        Button back = findViewById(R.id.button29);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IncomeActivity.this, adminpage.class);
                startActivity(intent);
            }
        });
    }

    private void loadIncomeData() {
        // Replace "dummy_username" with the actual logged-in username
        List<Income> incomeDataList = new IncomeManager(this).getAllIncome();
        incomeAdapter.setIncomeDataList(incomeDataList);
    }
}

