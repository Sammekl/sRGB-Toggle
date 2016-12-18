package com.sammekleijn.srgbtoggle.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by sammekleijn on 18/12/2016.
 */

public class SharedPreferenceHelper {

    private static SharedPreferenceHelper _instance;
    private SharedPreferences prefs;
    private static final String DEFAULT_PREF_NAME = "toggle_srgb";
    public static final String SRGB_ON = "SRGB_ON";

    public static SharedPreferenceHelper getInstance(Context context) {
        if (_instance == null) {
            _instance = new SharedPreferenceHelper(context);
        }
        return _instance;
    }

    private SharedPreferenceHelper(Context context) {
        this.prefs = context.getSharedPreferences(DEFAULT_PREF_NAME, Context.MODE_PRIVATE);
    }

    public Boolean getBoolean(String key) {
        return prefs.getBoolean(key, false);
    }

    public void setSRGBToggle(boolean srgbOn) {
        prefs.edit().putBoolean(SRGB_ON, srgbOn).apply();
    }
}
