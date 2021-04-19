package com.yds.gradle_simon;

import android.app.Application;

import com.yds.customize.util.BaseCustomizeUtil;
import com.yds.customize.util.crash.CrashExceptionHelper;
import com.yds.customize.util.crash.CrashExceptionListener;
import com.yds.gradle_simon.http.HttpHelper;
import com.yds.gradle_simon.http.XutilProcessor;

import org.jetbrains.annotations.NotNull;
import org.xutils.x;


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
        x.Ext.init(this);

        HttpHelper.getInstance().init(new XutilProcessor());

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
