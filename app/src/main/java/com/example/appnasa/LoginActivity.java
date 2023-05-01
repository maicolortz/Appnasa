package com.example.appnasa;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appnasa.db.User;
import com.example.appnasa.db.UserQueries;

public class LoginActivity extends AppCompatActivity {
    private Button loginButton;
    private EditText nameEditText;
    private EditText passwordEditText;
    private TextView text_view_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        nameEditText = findViewById(R.id.nameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        text_view_register = findViewById(R.id.text_view_register);


        loginButton.setOnClickListener(v -> {
            String name = nameEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            UserQueries userQueries = new UserQueries(LoginActivity.this);
            User user = userQueries.getUserByFirstnameAndPassword(name, password);

            if (user != null) {
                // User found, login successful
                ListAsteroidsByUser.start(LoginActivity.this, user.getId());
                Toast.makeText(LoginActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();

            } else {
                // User not found, login failed
                Toast.makeText(LoginActivity.this, "Invalid name or password!", Toast.LENGTH_SHORT).show();
            }
        });
        text_view_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterActivity.start(LoginActivity.this);
            }
        });
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }
}