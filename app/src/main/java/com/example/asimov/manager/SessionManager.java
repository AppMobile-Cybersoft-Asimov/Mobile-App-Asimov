package com.example.asimov.manager;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.asimov.R;

public class SessionManager {

    private static SessionManager instance;
    private final String USER_TOKEN = "user_token";
    private final SharedPreferences prefs;


    private SessionManager(Context context) {
        prefs = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);
    }

    public static void initiateInstance(Context context) {
        instance = new SessionManager(context);
    }

    public static SessionManager getInstance() {
        return instance;
    }

    public void saveAuthToken(String token) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(USER_TOKEN, token);
        editor.apply();
    }

    public String getAuthToken() {
        if (prefs.getString(USER_TOKEN, null) == null)
            return null;
        return "Bearer " + prefs.getString(USER_TOKEN, null);
    }

}
