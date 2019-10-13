package com.example.muhammadusama.volleydemo;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Muhammad Usama on 3/18/2019.
 */

public class SharedPreferenceManager {

    private static SharedPreferenceManager instance;
    private static Context ctx;

    private SharedPreferenceManager(Context context) {
        ctx = context;
    }

    public static synchronized SharedPreferenceManager getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPreferenceManager(context);
        }
        return instance;
    }

    public boolean userLogin(int id, String username, String email) {

        // Mode Private means that only this application can use this shared preference
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Constants.KEY_USER_ID, id);
        editor.putString(Constants.KEY_USER_EMAIL, email);
        editor.putString(Constants.KEY_USERNAME, username);

        // Apply changes to the editor
        editor.apply();

        return true;
    }

    public boolean isLoggedIn() {

        SharedPreferences sharedPreferences = ctx.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE);

        if (sharedPreferences.getString(Constants.KEY_USERNAME, null) != null) {

            return true;
        }

        return false;
    }

    public boolean logout() {

        SharedPreferences sharedPreferences = ctx.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //Remove all the values form editor
        editor.clear();
        editor.apply();
        return true;
    }

    public String getUserEmail() {

        SharedPreferences sharedPreferences = ctx.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(Constants.KEY_USER_EMAIL,null);
    }

    public String getUsername() {

        SharedPreferences sharedPreferences = ctx.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(Constants.KEY_USERNAME, null);
    }
}