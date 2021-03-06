package com.carles.sizematters.helper;

import android.content.Context;

import com.carles.sizematters.C;
import com.carles.sizematters.R;

public class PrefHelper {

    /**
     * Gets a language independent 'units selected' preference from the localizated string
     *
     * @param context
     * @param unitsSelected
     * @return the unitsSelected value language independent to store in preferences
     */
    public static String getUnitsSelectedPreferenceConstant(Context context, String unitsSelected) {
        String ret;
        if (context.getString(R.string.settings_units_inches).equals(unitsSelected)) {
            ret = C.USER_PREF_UNITS_INCHES;
        } else {
            ret = C.USER_PREF_UNITS_CMS;
        }
        return ret;
    }

    /**
     * Gets the localizated string from the language independent 'units selected' preference
     *
     * @param context
     * @param unitsSelectedConstant
     * @return units selected localized value
     */
    public static String getUnitsSelectedPreferenceString(Context context, String unitsSelectedConstant) {
        String ret;
        if (C.USER_PREF_UNITS_INCHES.equals(unitsSelectedConstant)) {
            ret = context.getString(R.string.settings_units_inches);
        } else {
            ret = context.getString(R.string.settings_units_cms);
        }
        return ret;
    }
}
