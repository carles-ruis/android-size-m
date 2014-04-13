package com.carles.sizematters.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.carles.sizematters.R;
import com.carles.sizematters.activity.MainActivity;
import com.carles.sizematters.adapter.GenderPagerAdapter;

import java.lang.reflect.Field;

public abstract class BaseContainerFragment extends Fragment {

    /*- ************************************************************************************************************** */
    /*- ************************************************************************************************************** */
    private class ThisOnPageChangeListener extends ViewPager.SimpleOnPageChangeListener {

        @Override
        public void onPageSelected(int position) {
            super.onPageSelected(position);
            activity().getApp().setGenderTabSelected(position);
        }
    }

    /*- ************************************************************************************************************** */
    /*- ************************************************************************************************************** */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_container, container, false);

        ViewPager pager = (ViewPager) view.findViewById(R.id.viewPager);
        pager.setAdapter(new GenderPagerAdapter(activity(), getChildFragmentManager()));

        /*- PagerSlidingTabStrip instead of PagerTabStrip in order to show all tabs in the screen */
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) view.findViewById(R.id.tabs);
        /*- tabs occupy all screen width */
        tabs.setShouldExpand(true);
        /*- match PagerSlidingTabStrip with the ViewPager */
        tabs.setViewPager(pager);
        /*- the ViewPager must delegate the OnPageListener implementation to the PagerSlidingTabStrip */
        tabs.setOnPageChangeListener(new ThisOnPageChangeListener());
        pager.setCurrentItem(activity().getApp().getGenderTabSelected());

        return view;
    }

    private MainActivity activity() {
        return (MainActivity) getActivity();
    }

    @Override
    /*- workaround to avoid bug for nested fragments (the child FragmentManager ends with a broken state when is detached from the activity */
    public void onDetach() {
        super.onDetach();
        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
