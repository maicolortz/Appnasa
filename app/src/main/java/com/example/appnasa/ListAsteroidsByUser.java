package com.example.appnasa;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appnasa.db.AsteroidQueries;
import com.example.appnasa.models.Asteroid;

import java.util.ArrayList;
import java.util.List;

public class ListAsteroidsByUser extends AppCompatActivity {
    private Button btnLogout;

    public static int userId; // El ID del usuario cuyos asteroides queremos mostrar
    List<String> datos = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_asteroids_by_user);
        Log.d("texto int", String.valueOf(userId));

        AsteroidQueries asteroidQueries = new AsteroidQueries(this);
        LinearLayout ln = findViewById(R.id.lp1);

        datos = new ArrayList<String>();

        List<Asteroid> asteroidList = asteroidQueries.getAsteroidByUserId(userId);
        for (int i = 0; i < asteroidList.size(); i++) {
            Asteroid asteroid = asteroidList.get(i);
            Button button = new Button(getApplicationContext());
            button.setTextSize(18);

            button.setText(asteroid.getName());

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AsteroidDetail.start(ListAsteroidsByUser.this, asteroid);
                    Toast.makeText((Context) ListAsteroidsByUser.this, (CharSequence) asteroid.getName(), Toast.LENGTH_SHORT).show();

                }
            });
            ln.addView(button);
        }

        btnLogout = findViewById(R.id.buttonLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity.start(ListAsteroidsByUser.this);
                Toast.makeText((Context) ListAsteroidsByUser.this, (CharSequence) "..logout, see you later!!", Toast.LENGTH_SHORT).show();

            }
        });


    }

    public static void start(Context context, int Id) {
        Intent intent = new Intent(context, ListAsteroidsByUser.class);
        userId = Id;
        context.startActivity(intent);

    }

    @Override
    public void onBackPressed() {
        // Es para evitar que se devuelva atras y que no tenga que volver a iniciar sesion
    }


}