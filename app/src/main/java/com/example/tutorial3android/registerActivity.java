package com.example.tutorial3android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

@SuppressWarnings("all")
public class registerActivity extends AppCompatActivity {
    private Button btn_login, btn_reset, btn_register;
    private EditText et_gmail, et_user, et_pass, et_again;
    private user_dbmanager userDBManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // find views
        btn_login = findViewById(R.id.login);
        btn_reset = findViewById(R.id.reset);
        btn_register = findViewById(R.id.register);
        et_gmail = findViewById(R.id.gmail);
        et_user = findViewById(R.id.user);
        et_pass = findViewById(R.id.pass);
        et_again = findViewById(R.id.again);
        userDBManager = new user_dbmanager(this);



        // listener
        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(registerActivity.this, "Reset", Toast.LENGTH_LONG).show();
                et_gmail.setText("");
                et_user.setText("");
                et_pass.setText("");
                et_again.setText("");
            }
        });


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(registerActivity.this, loginActivity.class);
                startActivity(intent);
            }
        });


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gmail = et_gmail.getText().toString();
                String username = et_user.getText().toString();
                String password = et_pass.getText().toString();
                String confirmPassword = et_again.getText().toString();

                if (!password.equals(confirmPassword)) {
                    Toast.makeText(registerActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                } else {
                    userRegisterProcess(gmail,username,password);
                }
            }
        });
    }

    private void userRegisterProcess(String gmail, String username, String password){
        try {
            userDBManager.insert(gmail,username,password);
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            userDBManager.close();
        }
        Toast.makeText(registerActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, loginActivity.class);
        startActivity(intent);
    }

    }
