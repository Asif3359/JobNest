package com.example.jobnest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    // Database info
    public static final String DBName = "SeekerDatabase.db";

    // Table and column names (must match EXACTLY what's in your database)
    public static final String TABLE_USER = "user";  // lowercase as in your schema
    public static final String COL_USER_ID = "UserID";
    public static final String COL_FIRST_NAME = "FirstName";
    public static final String COL_LAST_NAME = "LastName";  // NUMERIC in your schema
    public static final String COL_EMAIL = "Email";
    public static final String COL_PHONE = "PhoneNumber";
    public static final String COL_PASSWORD = "Password";
    public static final String COL_USER_ROLE = "UserRole";  // Not just "Role"

    public DBHelper(@Nullable Context context) {
        // Version number should match your existing DB version
        super(context, DBName, null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Empty since table already exists
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Empty if you don't need migrations
    }

    public boolean insertDataSeekerUser(String firstName, String lastName, String email,
                                        String phoneNumber, String password, String userRole) {
        SQLiteDatabase db = this.getWritableDatabase();

        // 1. Check if email exists (using correct column name)
        if (isEmailExists(db, email)) {
            return false;
        }

        // 2. Prepare data with EXACT column names
        ContentValues values = new ContentValues();
        values.put(COL_FIRST_NAME, firstName);
        values.put(COL_LAST_NAME, lastName);  // Will be stored as NUMERIC
        values.put(COL_EMAIL, email);
        values.put(COL_PHONE, phoneNumber);
        values.put(COL_PASSWORD, password);
        values.put(COL_USER_ROLE, userRole);  // Using "UserRole" not "Role"

        // 3. Insert using correct table name
        long result = db.insert(TABLE_USER, null, values);
        return result != -1;
    }

    private boolean isEmailExists(SQLiteDatabase db, String email) {
        // Query using correct column name
        Cursor cursor = db.query(TABLE_USER,
                new String[]{COL_EMAIL},  // Correct column
                COL_EMAIL + " = ?",       // Correct column in WHERE
                new String[]{email},
                null, null, null);

        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }


}