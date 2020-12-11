package com.yds.customize.util;

import android.util.Log;

/**
 * @author YDS
 * @date 2020/12/1
 * @discribe
 */
public class L {
    public static String TAG = "Simon";
    public static Boolean IS_SHOW = true;

    public static void setLogShow(Boolean is_show) {
        IS_SHOW = is_show;
    }

    public static void setLogFlag(String tag) {
        TAG = tag;
    }

    public static void i(String message) {
        if (IS_SHOW) {
            Log.i(TAG, message);
        }
    }

}
