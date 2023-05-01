package com.example.appnasa;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.appnasa.db.AsteroidQueries;
import com.example.appnasa.db.User;
import com.example.appnasa.db.UserQueries;
import com.example.appnasa.models.Asteroid;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText identificationEditText;
    private Button registerButton;

    RequestQueue queue;
    String Url = "https://api.nasa.gov/neo/rest/v1/feed?start_date=2023-04-27&end_date=2023-04-27&api_key=KaMMTiWSR44YDOvOYeZ65HdhIbrhQMfZb2K4mrxE";
    List<String> datos = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.editTextPassword);
        firstNameEditText = findViewById(R.id.editTextFirstName);
        lastNameEditText = findViewById(R.id.editTextLastName);
        identificationEditText = findViewById(R.id.editTextIdentification);
        registerButton = findViewById(R.id.buttonRegister);
        queue = Volley.newRequestQueue(this);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String firstName = firstNameEditText.getText().toString();
                String lastName = lastNameEditText.getText().toString();
                String identification = identificationEditText.getText().toString();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(lastName) || TextUtils.isEmpty(identification) || TextUtils.isEmpty(firstName)) {

                    Toast.makeText(RegisterActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();

                } else if (!email.matches(emailPattern)) {
                    // Muestra un mensaje si el email no es valido
                    Toast.makeText(RegisterActivity.this, "The email is not valid", Toast.LENGTH_SHORT).show();
                    return;
                }else if(identification.length()<6){
                    Toast.makeText(RegisterActivity.this, "The identification have six characters as minime", Toast.LENGTH_SHORT).show();
                }else if(password.length()<4){
                    Toast.makeText(RegisterActivity.this, "The password have four characters as minime", Toast.LENGTH_SHORT).show();
                }else {
                    User user = new User(1212, email, password, firstName, lastName, identification);
                    UserQueries userQueries = new UserQueries(RegisterActivity.this);
                    userQueries.addUser(user);
                    int userId = userQueries.getLastUserId();
                    Log.d("ultima id", String.valueOf(userId));
                    GetApiData(userId);

                    Toast.makeText(RegisterActivity.this, "User registered successfully!", Toast.LENGTH_SHORT).show();
                    LoginActivity.start(RegisterActivity.this);
                }


            }
        });

    }

    private void GetApiData(int userId) {
        datos = new ArrayList<String>();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, Url, null, new Response.Listener<JSONObject>() {
            final Gson gson = new Gson();

            @Override
            public void onResponse(JSONObject response) {
                // Manejar la respuesta de la API aquí

                try {
                    JSONArray data = response.getJSONObject("near_earth_objects").getJSONArray("2023-04-27");
                    Log.d("entra", "si");


                    for (int i = 0; i < data.length(); i++) {
                        JSONObject obj = data.getJSONObject(i);

                        String name = obj.getString("name");


                        Double absoluteMagnitudeH = obj.getDouble("absolute_magnitude_h");

                        Double estimatedDiameter = obj.getJSONObject("estimated_diameter").getJSONObject("kilometers").getDouble("estimated_diameter_min");

                        String is_potentially_hazardous_asteroid = obj.getString("is_potentially_hazardous_asteroid");


                        Log.d("entro", is_potentially_hazardous_asteroid);
                        Asteroid asteroid = new Asteroid(name, absoluteMagnitudeH, estimatedDiameter, is_potentially_hazardous_asteroid, userId);
                        AsteroidQueries asteroidQueries = new AsteroidQueries(RegisterActivity.this);
                        asteroidQueries.addAsteroid(asteroid);


                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Log.d("a", response.toString());

                int x = 0;

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Manejar el error de la API aqu
            }
        });
        queue.add(request);
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }
}