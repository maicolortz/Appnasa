package com.example.appnasa.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Nombre de la base de datos
    private static final String DATABASE_NAME = "asteroids.db";

    // Versión de la base de datos
    private static final int DATABASE_VERSION = 2;

    // Nombre de la tabla de usuarios y sus columnas
    public static final String TABLE_USERS = "users";
    public static final String COLUMN_USER_ID = "_id";
    public static final String COLUMN_USER_EMAIL = "email";
    public static final String COLUMN_USER_PASSWORD = "password";
    public static final String COLUMN_USER_FIRSTNAME = "firstname";
    public static final String COLUMN_USER_LASTNAME = "lastname";
    public static final String COLUMN_USER_IDENTIFICATION = "identification";
    public static final String COLUMN_USER_CREATED_AT = "created_at";
    public static final String COLUMN_USER_UPDATED_AT = "updated_at";


    // Nombre de la tabla de asteroides y sus columnas
    public static final String TABLE_ASTEROIDS = "asteroids";
    public static final String COLUMN_ASTEROID_ID = "_id";
    public static final String COLUMN_ASTEROID_NAME = "name";
    public static final String COLUMN_ASTEROID_ABSOLUTE_MAGNITUDE_H = "absolute_magnitude_h";
    public static final String COLUMN_ASTEROID_ESTIMATED_DIAMETER = "estimated_diameter";

    public static final String COLUMN_ASTEROID_IS_POTENTIALLY_HAZARDOUS_ASTEROID = "is_potentially_hazardous_asteroid";
    public static final String COLUMN_ASTEROID_USER_ID = "user_id";

    // Sentencia SQL para crear la tabla de usuarios

    private static final String CREATE_TABLE_USERS = "CREATE TABLE " + TABLE_USERS + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_USER_EMAIL + " TEXT,"
            + COLUMN_USER_PASSWORD + " TEXT,"
            + COLUMN_USER_FIRSTNAME + " TEXT,"
            + COLUMN_USER_LASTNAME + " TEXT,"
            + COLUMN_USER_IDENTIFICATION + " TEXT,"
            + COLUMN_USER_CREATED_AT + " DATETIME DEFAULT CURRENT_TIMESTAMP,"
            + COLUMN_USER_UPDATED_AT + " DATETIME DEFAULT CURRENT_TIMESTAMP"
            + ")";

    // Sentencia SQL para crear la tabla de asteroides
    private static final String CREATE_TABLE_ASTEROIDS = "CREATE TABLE " + TABLE_ASTEROIDS + "("
            + COLUMN_ASTEROID_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_ASTEROID_NAME + " TEXT,"
            + COLUMN_ASTEROID_ABSOLUTE_MAGNITUDE_H + " REAL,"
            + COLUMN_ASTEROID_ESTIMATED_DIAMETER + " REAL,"
            + COLUMN_ASTEROID_IS_POTENTIALLY_HAZARDOUS_ASTEROID + " TEXT,"
            + COLUMN_ASTEROID_USER_ID + " INTEGER,"
            + "FOREIGN KEY(" + COLUMN_ASTEROID_USER_ID + ") REFERENCES " + TABLE_USERS + "(" + COLUMN_USER_ID + ")"
            + ")";

    // Constructor de la clase
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crear la tabla de usuarios y la tabla de asteroides
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_ASTEROIDS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Borrar la tabla users si ya existe
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ASTEROIDS);

        // Crear la tabla users de nuevo
        onCreate(db);
    }

}

