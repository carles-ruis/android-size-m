package com.carles.sizematters.util;

import com.carles.sizematters.C;

public class Log {

    public static int i(String tag, String msg) {
        if (C.DEBUG) {
            return android.util.Log.i(tag, msg);
        } else {
            return 0;
        }
    }
}