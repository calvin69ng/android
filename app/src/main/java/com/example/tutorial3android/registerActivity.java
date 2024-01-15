package com.example.tutorial3android;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tutorial3android.data.User;
import com.example.tutorial3android.manager.UserManager;


@SuppressWarnings("all")
public class registerActivity extends AppCompatActivity {

    private EditText et_email, et_username, et_password, et_comfirm_password;
    private Button register;
    private UserManager UserManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextView textView = findViewById(R.id.login_turn_page_text);

        String text = "Login";

        SpannableString ss = new SpannableString(text);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Intent intent = new Intent(registerActivity.this, loginActivity.class);
                startActivity(intent);
            }
        };

        ss.setSpan(clickableSpan, 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());

        et_email = findViewById(R.id.email_register_edit_text);
        et_username = findViewById(R.id.user_register_edit_text);
        et_password = findViewById(R.id.password_register_edit_text);
        et_comfirm_password = findViewById(R.id.comfirm_password_register_edit_text);
        register = findViewById(R.id.register_button);
        UserManager = new UserManager(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = et_email.getText().toString();
                String username = et_username.getText().toString();
                String password = et_password.getText().toString();
                String comfirmPassword = et_comfirm_password.getText().toString();

                userRegisterProcess(email, username, password, comfirmPassword);
            }
        });
    }

    private void userRegisterProcess(String gmail, String username, String password, String comfirmPassword) {
        if (TextUtils.isEmpty(gmail) || TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty(comfirmPassword)) {
            Toast.makeText(registerActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
        } else if (!gmail.endsWith("@gmail.com")) {
            Toast.makeText(registerActivity.this, "Invalid gmail format. Please use a valid @gmail.com address", Toast.LENGTH_SHORT).show();
        } else if (!password.equals(comfirmPassword)) {
            Toast.makeText(registerActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
        } else {
            if (UserManager.isUserExists(username)) {
                Toast.makeText(registerActivity.this, "User with this username already exists", Toast.LENGTH_SHORT).show();
            } else if (UserManager.isGmailUserExists(gmail)) {
                Toast.makeText(registerActivity.this, "User with this Gmail address already exists", Toast.LENGTH_SHORT).show();
            } else {
                try {
                    UserManager.insertUser(new User(username, gmail, password));
                    Toast.makeText(registerActivity.this, "User registration successful", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    UserManager.close();
                }
                Intent intent = new Intent(this, loginActivity.class);
                startActivity(intent);
            }
        }
    }
}
