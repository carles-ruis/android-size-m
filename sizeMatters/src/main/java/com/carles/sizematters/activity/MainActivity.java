package com.carles.sizematters.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.carles.sizematters.C;
import com.carles.sizematters.R;
import com.carles.sizematters.adapter.DrawerAdapter;
import com.carles.sizematters.adapter.DrawerOption;
import com.carles.sizematters.fragment.DressFragment;
import com.carles.sizematters.fragment.HelpDialogFragment;
import com.carles.sizematters.fragment.JacketFragment;
import com.carles.sizematters.fragment.ShirtContainerFragment;
import com.carles.sizematters.fragment.ShoesContainerFragment;
import com.carles.sizematters.fragment.SizesContainerFragment;
import com.carles.sizematters.fragment.TrousersFragment;

public class MainActivity extends BaseActivity {
    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private ActionBarDrawerToggle drawerToggle;
    private DrawerOption selectedOption = null;

    /*- ************************************************************************************************************** */
    /*- ************************************************************************************************************** */
    private class ThisActionBarDrawerToggle extends ActionBarDrawerToggle {
        public ThisActionBarDrawerToggle(Activity activity, DrawerLayout drawerLayout, int drawerImageRes, int openDrawerContentDescRes, int closeDrawerContentDescRes) {
            super(activity, drawerLayout, drawerImageRes, openDrawerContentDescRes, closeDrawerContentDescRes);
        }

        /**
         * Called when a drawer has settled in a completely closed state.
         */
        @Override
        public void onDrawerClosed(View view) {
            super.onDrawerClosed(view);

            if (selectedOption != null) {
                getSupportActionBar().setTitle(getString(selectedOption.titleId));
            }
            supportInvalidateOptionsMenu();
        }

        /**
         * Called when a drawer has settled in a completely open state.
         */
        @Override
        public void onDrawerOpened(View drawerView) {
            super.onDrawerOpened(drawerView);
            getSupportActionBar().setTitle(getTitle());
            supportInvalidateOptionsMenu();
        }
    }

    /*- ************************************************************************************************************** */
    /*- ************************************************************************************************************** */
    private class ThisDrawerItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }

    }

    /*- ************************************************************************************************************** */
    /*- ************************************************************************************************************** */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        /* Initialize the drawer list */
        drawerList = (ListView) findViewById(R.id.left_drawer);
        drawerList.setAdapter(new DrawerAdapter(this));
        drawerList.setOnItemClickListener(new ThisDrawerItemClickListener());

        /*- enable ActionBar app icon to behave as action to toggle nav drawer */
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        /* Open and close with the app icon */
        drawerToggle = new ThisActionBarDrawerToggle(this, drawerLayout, R.drawable.ic_navigation_drawer, R.string.access_drawer_open, R.string.access_drawer_close);
        drawerLayout.setDrawerListener(drawerToggle);

        /*- callback when a fragment is added to the back stack */
        // getSupportFragmentManager().addOnBackStackChangedListener(new ThisBackStackChangedListener());

        initFromPreferences();

    }

    private void initFromPreferences() {

        /*- called only the first time the app is opened in the device*/
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

        /*- open drawer if last selection isn't stored in preferences */
        Integer lastOptionSelected = getApp().getDrawerOptionSelected();
        if (lastOptionSelected == C.PREF_NOT_EXISTS) {
            drawerLayout.openDrawer(drawerList);
        } else {
            selectItem(lastOptionSelected);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean drawerOpen = drawerLayout.isDrawerOpen(drawerList);
        if (drawerOpen) {

            /*- If the nav drawer is open, hide action items related to the content view */
            menu.findItem(R.id.action_spinner).setVisible(false);
            menu.findItem(R.id.action_help).setVisible(false);
            menu.findItem(R.id.action_settings).setVisible(false);

            return true;

        } else {

            menu.findItem(R.id.action_spinner).setVisible(false);
            menu.findItem(R.id.action_help).setVisible(true);
            menu.findItem(R.id.action_settings).setVisible(true);

            /*- delegate options menu handling to fragments */
            return super.onPrepareOptionsMenu(menu);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        /*- The ActionBarDrawerToggle handles the app icon touch event */
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        /* Handle your other action bar items... */
        switch (item.getItemId()) {
            case R.id.action_settings:

                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.slide_activity_to_left_in, R.anim.slide_activity_to_left_out);
                break;

            case R.id.action_spinner:
                break;

            case R.id.action_help:
                if (selectedOption != null) {
                    this.showHelpDialog();
                }
                break;

            default: /*- fragment will handle the event */
                return super.onOptionsItemSelected(item);
        }

        /*- event has been handled by the activity */
        return true;
    }

    private void showHelpDialog() {
        int titleId = selectedOption.titleId;
        int helpMessageId = selectedOption.helpMessageId;

        HelpDialogFragment help = HelpDialogFragment.newInstance(titleId, helpMessageId);

        /*- Doesn't work. As HelpDialogFragment extends DialogFragment, we have to set a theme to animate it */
        //        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        //        ft.setCustomAnimations(R.anim.zoom_in, FragmentTransaction.TRANSIT_NONE);

        /*- DialogFragment.show() starts a transaction that adds the Dialog on top of the current shown activity */
        help.show(getSupportFragmentManager(), C.TAG_HELP_DIALOG);

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    /* Swaps fragments in the main content view */
    private void selectItem(int position) {

        DrawerOption option = DrawerOption.values()[position];
        if (option != selectedOption) {

            Fragment fragment = new Fragment();

            switch (option) {
                case DRESS:
                    fragment = new DressFragment();
                    break;
                case JACKET:
                    fragment = new JacketFragment();
                    break;
                case SHIRT:
                    fragment = new ShirtContainerFragment();
                    break;
                case SHOES:
                    fragment = new ShoesContainerFragment();
                    break;
                case TROUSERS:
                    fragment = new TrousersFragment();
                    break;
                case BY_GENDER:
                    // args.putString(C.PARAM_INITIAL_TAB, C.TAB_MEN);
                    // fragment = ByGenderFragment.newInstance(args);
                    fragment = new SizesContainerFragment();
                    break;
            }

            selectedOption = option;

                /*- Insert the fragment by replacing any existing fragment */
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();

                /*- Highlight the selected item, update the title ... and close the drawer */
            drawerList.setItemChecked(position, true);
            getSupportActionBar().setTitle(getString(selectedOption.titleId));
                /*- recreate the action bar by calling onCreateOptionsMenu */
            supportInvalidateOptionsMenu();

                /*- store new selection in preferences to retrieve it in next app execution */
            getApp().setDrawerOptionSelected(position);

        }

        drawerLayout.closeDrawer(drawerList);

    }

    /*- ************************************************************************************************************** */
    /*- ************************************************************************************************************** */
    public DrawerOption getSelectedOption() {
        return selectedOption;
    }
}
