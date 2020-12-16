package com.yds.gradle_simon;

import android.app.Application;

import com.yds.customize.util.BaseCustomizeUtil;


/**
 * @author YDS
 * @date 2020/12/1
 * @discribe
 */
public class MyApplication extends Application {
    private static MyApplication instance;

    public static MyApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = (MyApplication) this.getApplicationContext();
        BaseCustomizeUtil.init(this);

    }
}
