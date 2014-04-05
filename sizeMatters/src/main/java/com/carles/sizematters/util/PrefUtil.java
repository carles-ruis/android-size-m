package com.carles.sizematters.util;

import android.content.SharedPreferences;

import com.google.gson.Gson;

public class PrefUtil {

    public static <T> T get(SharedPreferences preferences, String key, Class<T> clazz) {

        final Gson gson = new Gson();
        final String json = preferences.getString(key, null);
        if (json != null) {
            return gson.fromJson(json, clazz);
        } else {
            return null;
        }
    }

    public static void put(SharedPreferences preferences, String key, Object object) {

        final SharedPreferences.Editor editor = preferences.edit();
        if (object != null) {
            final Gson gson = new Gson();
            final String json = gson.toJson(object);
            editor.putString(key, json);
        } else {
            editor.remove(key);
        }
        editor.commit();
    }
}
