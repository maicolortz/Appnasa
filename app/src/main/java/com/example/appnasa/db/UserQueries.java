package com.example.appnasa.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class UserQueries {

    private final DatabaseHelper dbHelper;

    public UserQueries(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public int getLastUserId() {
        int lastId = 0;
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {"MAX(" + DatabaseHelper.COLUMN_USER_ID + ") AS max_id"};

        Cursor cursor = db.query(DatabaseHelper.TABLE_USERS, projection, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            lastId = cursor.getInt(cursor.getColumnIndex("max_id"));
        }

        cursor.close();
        db.close();
        return lastId;
    }

    public void addUser(User user) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_USER_EMAIL, user.getEmail());
        values.put(DatabaseHelper.COLUMN_USER_PASSWORD, user.getPassword());
        values.put(DatabaseHelper.COLUMN_USER_FIRSTNAME, user.getFirstName());
        values.put(DatabaseHelper.COLUMN_USER_LASTNAME, user.getLastName());
        values.put(DatabaseHelper.COLUMN_USER_IDENTIFICATION, user.getIdentification());

        db.insert(DatabaseHelper.TABLE_USERS, null, values);
        db.close();
    }

    public User getUserByFirstnameAndPassword(String name, String password) {
        User user = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {DatabaseHelper.COLUMN_USER_ID, DatabaseHelper.COLUMN_USER_EMAIL, DatabaseHelper.COLUMN_USER_FIRSTNAME, DatabaseHelper.COLUMN_USER_LASTNAME, DatabaseHelper.COLUMN_USER_IDENTIFICATION};

        String selection = DatabaseHelper.COLUMN_USER_FIRSTNAME + " = ? AND " + DatabaseHelper.COLUMN_USER_PASSWORD + " = ?";
        String[] selectionArgs = {name, password};

        Cursor cursor = db.query(DatabaseHelper.TABLE_USERS, projection, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            user = new User();
            user.setId(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_USER_ID)));
            user.setEmail(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_USER_EMAIL)));
            user.setFirstName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_USER_FIRSTNAME)));
            user.setLastName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_USER_LASTNAME)));
            user.setIdentification(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_USER_IDENTIFICATION)));
        }

        cursor.close();
        db.close();
        return user;
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(DatabaseHelper.TABLE_USERS, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            User user = new User();
            user.setId(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_USER_ID)));
            user.setEmail(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_USER_EMAIL)));
            user.setFirstName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_USER_FIRSTNAME)));
            user.setLastName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_USER_LASTNAME)));
            user.setIdentification(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_USER_IDENTIFICATION)));
            users.add(user);
        }

        cursor.close();
        db.close();
        return users;
    }

}
