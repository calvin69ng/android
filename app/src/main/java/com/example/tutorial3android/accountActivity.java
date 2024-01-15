package com.example.tutorial3android;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.tutorial3android.manager.UserManager;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class accountActivity extends AppCompatActivity {

    private UserManager UserManager;
    private ListView listView;
    private Button backButton;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        listView = findViewById(R.id.listview4);
        backButton = findViewById(R.id.button9);

        UserManager = new UserManager(this);

        // Fetch usernames from the database
        List<String> usernames = getUsernames();

        // Create an adapter and set it to the ListView
        adapter = new account_adapter(this, android.R.layout.simple_list_item_1, usernames);
        listView.setAdapter(adapter);


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(accountActivity.this, adminpage.class);
                startActivity(intent);
            }
        });
    }

    public List<String> getUsernames() {
        List<String> usernames = new ArrayList<>();

        Cursor cursor = UserManager.fetchAllUsers();

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    String username = cursor.getString(cursor.getColumnIndex(UserManager.COLUMN_USERNAME));
                    usernames.add(username);
                } while (cursor.moveToNext());
            }

            cursor.close();
        }

        return usernames;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UserManager.close();
    }
}