package com.example.appnasa;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String API_KEY = "KaMMTiWSR44YDOvOYeZ65HdhIbrhQMfZb2K4mrxE";
    private static final String BASE_URL = "https://api.nasa.gov/neo/rest/v1/feed/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button boton2 = findViewById(R.id.button3);
        Button boton = findViewById(R.id.boton_id);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterActivity.start(MainActivity.this);
            }
        });
        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.start(MainActivity.this);
            }
        });

    }

    public static void start(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);

    }

}