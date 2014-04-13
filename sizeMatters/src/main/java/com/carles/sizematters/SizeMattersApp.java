package com.carles.sizematters;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.preference.PreferenceManager;

import com.carles.sizematters.util.Log;
import com.carles.sizematters.util.PrefUtil;

public class SizeMattersApp extends Application {

    private static final String LOG_TAG = "SizeMattersApp";

    /*- All activities share data through the SharedPreferences object which is accessed from the SizeMatterApp singleton */
    private SharedPreferences prefs;

    /*- ************************************************************************************************************** */
    /*- ************************************************************************************************************** */
    @Override
    public void onCreate() {
        super.onCreate();

        configStrictMode();

        prefs = PreferenceManager.getDefaultSharedPreferences(this);

    }

    private void configStrictMode() {
        if (C.DEBUG) {
            StrictMode.enableDefaults();
            Log.i(LOG_TAG, "Strict Mode on");
        } else {
            Log.i(LOG_TAG, "Strict Mode off");
        }
    }

    public int getDrawerOptionSelected() {
        return prefs.getInt(C.APP_PREF_LAST_DRAWER_OPTION_SELECTED, C.PREF_NOT_EXISTS);
    }

    public void setDrawerOptionSelected(int drawerOptionSelected) {
        putPreference(C.APP_PREF_LAST_DRAWER_OPTION_SELECTED, drawerOptionSelected);
    }

    public int getGenderTabSelected() {
        return prefs.getInt(C.APP_PREF_LAST_GENDER_TAB_SELECTED, 0);
    }

    public void setGenderTabSelected(int genderTabSelected) {
        putPreference(C.APP_PREF_LAST_GENDER_TAB_SELECTED, genderTabSelected);
    }

    public Boolean getShowSizesUk() {
        return prefs.getBoolean(C.USER_PREF_SHOW_SIZES_UK, false);
    }

//    public void setShowSizesUk(Boolean showSizesUk) {
//        putPreference(C.USER_PREF_SHOW_SIZES_UK, showSizesUk);
//    }

    public Boolean getShowSizesJp() {
        return prefs.getBoolean(C.USER_PREF_SHOW_SIZES_JP, false);
    }

//    public void setShowSizesJp(Boolean showSizesJp) {
//        putPreference(C.USER_PREF_SHOW_SIZES_JP, showSizesJp);
//    }

//    public String getUnitsSelected() {
//        return prefs.getString(C.USER_PREF_UNITS, getString(R.string.settings_units_cms));
//    }

//    public void setUnitsSelected(String unitsSelected) {
//        putPreference(C.USER_PREF_UNITS, unitsSelected);
//    }

    private void putPreference(String key, Object value) {
        final SharedPreferences.Editor editor = prefs.edit();

        if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
            /*- it is safer to call commit than to call apply because commit it's synchronous */
            editor.commit();
        } else if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
            editor.commit();
        } else if (value instanceof String) {
            editor.putString(key, (String)value);
            editor.commit();
        } else {
            PrefUtil.put(prefs, key, value);
        }
    }

    public String getUnitsSelectedConstant() {
        return prefs.getString(C.USER_PREF_UNITS_CONSTANT, C.USER_PREF_UNITS_CMS);
    }

    public void setUnitsSelectedConstant(String unitsSelectedConstant) {
        putPreference(C.USER_PREF_UNITS_CONSTANT, unitsSelectedConstant);
    }

}