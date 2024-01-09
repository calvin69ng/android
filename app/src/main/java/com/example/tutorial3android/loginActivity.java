package com.example.tutorial3android;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class loginActivity extends AppCompatActivity {

    private Button btn_next, btn_reset, btn_register;

    private EditText et_user, et_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // find views
        btn_next = findViewById(R.id.login_login_success);
        btn_reset = findViewById(R.id.reset2);
        btn_register = findViewById(R.id.register);
        et_user = findViewById(R.id.user1);
        et_pass = findViewById(R.id.pass1);

        /*btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(loginActivity.this, "Reset", Toast.LENGTH_LONG).show();
                et_user.setText("");
                et_pass.setText("");
            }
        });*/

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the entered username and password
                String enteredUsername = et_user.getText().toString().trim();
                String enteredPassword = et_pass.getText().toString().trim();

                // Check if the entered credentials match a user in the SQLite database
                if (isUserValid(enteredUsername, enteredPassword)) {
                    // Credentials are valid, store the user information
                    storeUserInfo(enteredUsername);

                    // Navigate to the next activity
                    Intent intent = new Intent(loginActivity.this, login_success.class);

                    startActivity(intent);
                } else {
                    // Credentials are invalid, show a message
                    Toast.makeText(loginActivity.this, "Invalid username or password", Toast.LENGTH_LONG).show();
                }
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginActivity.this, registerActivity.class);
                startActivity(intent);
            }
        });
    }

    // Method to store the user information in SharedPreferences
    private void storeUserInfo(String username) {
        SharedPreferences preferences = getSharedPreferences("user_info", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("username", username);
        editor.apply();
    }

    // Method to check if the entered credentials match a user in the SQLite database
    private boolean isUserValid(String username, String password) {
        // Implement the logic to check the credentials in your SQLite database
        // You might want to use a SQLiteOpenHelper or other methods to interact with the database
        // For simplicity, let's assume you have a method in a DatabaseHelper class like checkUserCredentials
        // that returns true if the user with the given username and password exists in the database
        dbhelper dbHelper = new dbhelper(loginActivity.this);
        return dbHelper.checkUserCredentials(username, password);
    }
}
