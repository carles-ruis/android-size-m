package com.carles.sizematters.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.carles.sizematters.C;
import com.carles.sizematters.R;
import com.carles.sizematters.activity.MainActivity;
import com.carles.sizematters.fragment.MenShirtFragment;
import com.carles.sizematters.fragment.MenShoesFragment;
import com.carles.sizematters.fragment.MenSizesFragment;
import com.carles.sizematters.fragment.WomenShirtFragment;
import com.carles.sizematters.fragment.WomenShoesFragment;
import com.carles.sizematters.fragment.WomenSizesFragment;

public class GenderPagerAdapter extends FragmentPagerAdapter {

    private Context context;

    public GenderPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }
    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        DrawerOption option = ((MainActivity) context).getSelectedOption();

        switch (position) {
            case C.TAB_MEN_POSITION:
                if (option == DrawerOption.SHOES) {
                    fragment = new MenShoesFragment();
                } else if (option == DrawerOption.SHIRT) {
                    fragment = new MenShirtFragment();
                } else if (option == DrawerOption.BY_GENDER) {
                    fragment = new MenSizesFragment();
                }
                break;
            case C.TAB_WOMEN_POSITION:
                if (option == DrawerOption.SHOES) {
                    fragment = new WomenShoesFragment();
                } else if (option == DrawerOption.SHIRT) {
                    fragment = new WomenShirtFragment();
                } else if (option == DrawerOption.BY_GENDER) {
                    fragment = new WomenSizesFragment();
                }
                break;
        }

        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == C.TAB_MEN_POSITION) {
            return context.getString(R.string.tab_men);
        } else if (position == C.TAB_WOMEN_POSITION) {
            return context.getString(R.string.tab_women);
        }
        return "";
    }
}