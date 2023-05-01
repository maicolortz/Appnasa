package com.example.appnasa.db;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.appnasa.models.Asteroid;

import java.util.ArrayList;
import java.util.List;

public class AsteroidQueries {

    private final DatabaseHelper dbHelper;

    public AsteroidQueries(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void addAsteroid(Asteroid asteroid) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_ASTEROID_NAME, asteroid.getName());
        values.put(DatabaseHelper.COLUMN_ASTEROID_ABSOLUTE_MAGNITUDE_H, asteroid.getAbsoluteMagnitudeH());
        values.put(DatabaseHelper.COLUMN_ASTEROID_ESTIMATED_DIAMETER, asteroid.getEstimatedDiameter());
        values.put(DatabaseHelper.COLUMN_ASTEROID_IS_POTENTIALLY_HAZARDOUS_ASTEROID, asteroid.getIs_potentially_hazardous_asteroid());
        values.put(DatabaseHelper.COLUMN_ASTEROID_USER_ID, asteroid.getUserId());

        db.insert(DatabaseHelper.TABLE_ASTEROIDS, null, values);
        db.close();
    }

    public List<Asteroid> getAsteroidByUserId(int userId) {
        List<Asteroid> asteroids = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                DatabaseHelper.COLUMN_ASTEROID_NAME,
                DatabaseHelper.COLUMN_ASTEROID_ABSOLUTE_MAGNITUDE_H,
                DatabaseHelper.COLUMN_ASTEROID_ESTIMATED_DIAMETER,
                DatabaseHelper.COLUMN_ASTEROID_IS_POTENTIALLY_HAZARDOUS_ASTEROID,
                DatabaseHelper.COLUMN_ASTEROID_USER_ID
        };

        String selection = DatabaseHelper.COLUMN_ASTEROID_USER_ID + " = ?";
        String[] selectionArgs = {String.valueOf(userId)};

        Cursor cursor = db.query(
                DatabaseHelper.TABLE_ASTEROIDS,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            Asteroid asteroid = new Asteroid();
            asteroid.setName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_ASTEROID_NAME)));
            asteroid.setAbsoluteMagnitudeH(cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.COLUMN_ASTEROID_ABSOLUTE_MAGNITUDE_H)));
            asteroid.setEstimatedDiameter(cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.COLUMN_ASTEROID_ESTIMATED_DIAMETER)));
            asteroid.setIs_potentially_hazardous_asteroid(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_ASTEROID_IS_POTENTIALLY_HAZARDOUS_ASTEROID)));
            asteroid.setUserId(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ASTEROID_USER_ID)));
            asteroids.add(asteroid);
        }

        cursor.close();
        db.close();
        return asteroids;
    }

    public List<Asteroid> getAsteroids() {
        List<Asteroid> asteroids = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(DatabaseHelper.TABLE_ASTEROIDS, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            Asteroid asteroid = new Asteroid();
            asteroid.setName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_ASTEROID_NAME)));
            asteroid.setAbsoluteMagnitudeH(cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.COLUMN_ASTEROID_ABSOLUTE_MAGNITUDE_H)));
            asteroid.setEstimatedDiameter(cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.COLUMN_ASTEROID_ESTIMATED_DIAMETER)));
            asteroid.setIs_potentially_hazardous_asteroid(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_ASTEROID_IS_POTENTIALLY_HAZARDOUS_ASTEROID)));
            asteroid.setUserId(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ASTEROID_USER_ID)));
            asteroids.add(asteroid);
        }

        cursor.close();
        db.close();
        return asteroids;
    }

    public Asteroid getAsteroidById(int asteroidId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                DatabaseHelper.COLUMN_ASTEROID_ID,
                DatabaseHelper.COLUMN_ASTEROID_NAME,
                DatabaseHelper.COLUMN_ASTEROID_ABSOLUTE_MAGNITUDE_H,
                DatabaseHelper.COLUMN_ASTEROID_ESTIMATED_DIAMETER,
                DatabaseHelper.COLUMN_ASTEROID_IS_POTENTIALLY_HAZARDOUS_ASTEROID,
                DatabaseHelper.COLUMN_ASTEROID_USER_ID
        };

        String selection = DatabaseHelper.COLUMN_ASTEROID_ID + " = ?";
        String[] selectionArgs = {String.valueOf(asteroidId)};

        Cursor cursor = db.query(
                DatabaseHelper.TABLE_ASTEROIDS,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        Asteroid asteroid = null;

        if (cursor.moveToFirst()) {
            asteroid = new Asteroid();
            asteroid.setName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_ASTEROID_NAME)));
            asteroid.setAbsoluteMagnitudeH(cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.COLUMN_ASTEROID_ABSOLUTE_MAGNITUDE_H)));
            asteroid.setEstimatedDiameter(cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.COLUMN_ASTEROID_ESTIMATED_DIAMETER)));
            asteroid.setIs_potentially_hazardous_asteroid(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_ASTEROID_IS_POTENTIALLY_HAZARDOUS_ASTEROID)));
            asteroid.setUserId(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ASTEROID_USER_ID)));
        }

        cursor.close();
        db.close();

        return asteroid;
    }
}
