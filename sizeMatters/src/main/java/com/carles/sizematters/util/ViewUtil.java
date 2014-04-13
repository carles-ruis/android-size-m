package com.carles.sizematters.util;

import android.content.Context;
import android.content.res.Resources;

public class ViewUtil {

    public static int getDps(Context ctx, int resId) {
        Resources res = ctx.getResources();
        int dp = (int) (res.getDimension(resId) / res.getDisplayMetrics().density);
        return dp;
    }

    /**
     * Converts Density Independent Pixels (dip, dp) to Pixels (px)
     */
    public static int dipsToPixels(Context ctx, int dips) {
        final float density = ctx.getResources().getDisplayMetrics().density;
        return (int) (dips * density);
    }

}
