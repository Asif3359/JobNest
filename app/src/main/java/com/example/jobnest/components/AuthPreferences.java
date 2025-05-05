package com.example.jobnest.components;

import android.content.Context;
import android.content.SharedPreferences;

public class AuthPreferences {
    private static final String PREF_NAME = "JobNestPrefs";
    private static final String KEY_USER_ROLE = "user_role";

    private final SharedPreferences sharedPreferences;

    public AuthPreferences(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void saveUserRole(String role) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USER_ROLE, role);
        editor.apply();
    }

    public String getUserRole() {
        return sharedPreferences.getString(KEY_USER_ROLE, null);
    }

    public void clearUserData() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(KEY_USER_ROLE);
        editor.apply();
    }
}