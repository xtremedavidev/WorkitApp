package com.example.workit;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {

    private static final String SHARED_PREF_NAME = "my_shared_pref";
    private static final String KEY_DAY_COMPLETED = "day_completed_";
    private static final String KEY_DATE_COMPLETED = "date_completed_";

    private static SharedPreferences sharedPreferences;
    private static SharedPrefManager mInstance;

    private SharedPrefManager(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    public void setDayCompleted(int day) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_DAY_COMPLETED + day, true);
        editor.apply();
    }

    public boolean isDayCompleted(int day) {
        return sharedPreferences.getBoolean(KEY_DAY_COMPLETED + day, false);
    }

    public void setDateCompleted(int day, String date) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_DATE_COMPLETED + day, date);
        editor.apply();
    }

    public String getDateCompleted(int day) {
        return sharedPreferences.getString(KEY_DATE_COMPLETED + day, "");
    }
}
