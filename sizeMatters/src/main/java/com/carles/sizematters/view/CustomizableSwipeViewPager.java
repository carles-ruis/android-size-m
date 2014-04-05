package com.carles.sizematters.view;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * ViewPager extension that allows to disable the pager swipe functionality. By default the paging is enabled in
 * portrait mode and disabled in landscape mode in order to delegate the swipe to a HorizontalScrollView
 */
public class CustomizableSwipeViewPager extends ViewPager {

    private boolean enableSwipe = true;

    public CustomizableSwipeViewPager(Context context) {
        super(context);
        setIfMustEnableSwipe();
    }

    public CustomizableSwipeViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        setIfMustEnableSwipe();
    }

    private void setIfMustEnableSwipe() {
        enableSwipe = getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
    }

    /*- ************************************************************************************************************** */
    /*- ************************************************************************************************************** */

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (this.enableSwipe) {
            return super.onTouchEvent(event);
        }

        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (this.enableSwipe) {
            return super.onInterceptTouchEvent(event);
        }

        return false;
    }

    public void setPagingEnabled(boolean enabled) {
        this.enableSwipe = enabled;
    }

}
