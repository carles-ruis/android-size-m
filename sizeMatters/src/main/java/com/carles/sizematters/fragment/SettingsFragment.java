package com.carles.sizematters.fragment;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.Preference;
import android.support.v4.preference.PreferenceFragment;

import com.carles.sizematters.C;
import com.carles.sizematters.R;
import com.carles.sizematters.activity.BaseActivity;
import com.carles.sizematters.helper.PrefHelper;

public class SettingsFragment extends PreferenceFragment implements OnSharedPreferenceChangeListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);

        /*- I have to set summary for the ListPreference explicity , PreferenceFragment doesn't set it by itself */
        String summary = ((BaseActivity) getActivity()).getApp().getUnitsSelected();
        Preference unitsPreference = findPreference(C.USER_PREF_UNITS);
        unitsPreference.setSummary(summary);

    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Preference preferenceChanged;

        if (key.equals(C.USER_PREF_UNITS)) {
            preferenceChanged = findPreference(key);

            /*- Set summary to be the user-description for the selected value */
            String unitsSelected = sharedPreferences.getString(key, getString(R.string.settings_units_cms));
            preferenceChanged.setSummary(unitsSelected);

            /*- Set units selected preference language independent */
            String unitsSelectedConstant = PrefHelper.getUnitsSelectedPreferenceConstant(getActivity(), unitsSelected);
            ((BaseActivity) getActivity()).getApp().setUnitsSelectedConstant(unitsSelectedConstant);
        }
    }

}
