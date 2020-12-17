package com.yds.customize.util;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.RequiresApi;

public class SoftKeyBoardUtil {
    /**
     * 展示软键盘
     * @param view    :一般为EditText
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void showKeyboard(View view) {
        InputMethodManager manager = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.showSoftInput(view, 0);
    }

    /**
     * 隐藏软键盘
     * @param view    :一般为EditText
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void hideKeyboard(View view) {
        InputMethodManager manager = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
