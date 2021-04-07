package com.yds.gradle_simon;

import android.app.Application;

import com.yds.customize.util.BaseCustomizeUtil;
import com.yds.customize.util.crash.CrashExceptionHelper;
import com.yds.customize.util.crash.CrashExceptionListener;

import org.jetbrains.annotations.NotNull;


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


        CrashExceptionHelper.INSTANCE.caughtException(instance, new CrashExceptionListener() {
            @Override
            public void crashMainException(@NotNull String crashMainMsg) {

            }

            @Override
            public void crashThreadException(@NotNull String crashThreadMsg) {

            }
        });
    }
}
