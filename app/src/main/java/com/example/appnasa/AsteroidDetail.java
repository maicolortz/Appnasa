package com.example.appnasa;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appnasa.models.Asteroid;

public class AsteroidDetail extends AppCompatActivity {
    public static Asteroid asteroidData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asteroid_detail);
        TextView asteroidName = findViewById(R.id.asteroidName);
        TextView asteroidpotentially_hazardous = findViewById(R.id.asteroidpotentially_hazardous);
        TextView asteroidAbsoluteMagnitudeH = findViewById(R.id.asteroidAbsoluteMagnitudeH);
        TextView asteroidEstimatedDiameter = findViewById(R.id.asteroidEstimatedDiameter);

        asteroidName.setText(asteroidData.getName());
        asteroidpotentially_hazardous.setText(asteroidData.getIs_potentially_hazardous_asteroid());
        asteroidAbsoluteMagnitudeH.setText(String.valueOf((double) asteroidData.getAbsoluteMagnitudeH()));
        asteroidEstimatedDiameter.setText(String.valueOf((double) asteroidData.getEstimatedDiameter()));


    }

    public static void start(Context context, Asteroid aster) {
        Intent intent = new Intent(context, AsteroidDetail.class);
        context.startActivity(intent);
        asteroidData = aster;
    }
}