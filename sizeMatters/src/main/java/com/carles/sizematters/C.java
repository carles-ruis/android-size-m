package com.carles.sizematters;

public class C {

    /*- Logging */
    public static final boolean DEBUG = false;
    public static final String LOG_TAG = "SizeMattersApp";

    /*- Preferences files */
    // public static final String USER_PREF_FILE = "user_prefs";
    // public static final String APP_PREF_FILE = "app_prefs";

    /*- Preferences keys */
    public static final String APP_PREF_LAST_DRAWER_OPTION_SELECTED = "pref_last_option_selected";
    public static final String APP_PREF_LAST_GENDER_TAB_SELECTED = "pref_last_gender_selected";
    public static final String USER_PREF_SHOW_SIZES_UK = "pref_show_size_uk";
    public static final String USER_PREF_SHOW_SIZES_JP = "pref_show_size_jp";
    public static final String USER_PREF_UNITS = "pref_units";
    public static final String USER_PREF_UNITS_CONSTANT = "pref_units_constant";

    /*- Preference possible values */
    public static final int PREF_NOT_EXISTS = -1;
    public static final String PREF_NOT_EXIST$ = "pref_not_exists";
    public static final String USER_PREF_UNITS_CMS = "pref_units_cms";
    public static final String USER_PREF_UNITS_INCHES = "pref_units_inches";

    /*- Bundle args */
    public static final String PARAM_INITIAL_TAB = "param_initial_tab";
    public static final String TAB_MEN = "tab_men";
    public static final String TAB_WOMEN = "tab_women";

    /*- tab navigation positions */
    public static final int TAB_MEN_POSITION = 0;
    public static final int TAB_WOMEN_POSITION = 1;

    /*- Raw files markers */
    public static final char FILE_COMMENT_MARKER = '#';
    public static final String RANGE_SEPARATOR = "-";
    public static final String UNKNOWN_VALUE = "?";

    /*- Tags for identify fragments */
    public static final String TAG_HELP_DIALOG = "helpDialog";

    /*- Bugsense */
    public static final String API_KEY = "5dac9455";
    public static final String COMMON_USER_ID = "0";
    public static final String OPEN_RESOURCE = "open_raw_resource";
}
