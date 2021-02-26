package com.yds.customize.util;

import android.app.Application;
import android.content.Context;

/**
 * @author YDS
 * @date 2020/12/10
 * @discribe 基础工具类获取MyApplication上下文
 */
public class BaseCustomizeUtil {
    public static Context mContext;

    public static void init(Application app) {
        if (mContext == null) {
            mContext = app.getApplicationContext();

            CrashManagerUtil.getInstance(mContext);
        }
    }
}
