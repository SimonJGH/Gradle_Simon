package com.yds.customize.util.crash;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.yds.customize.util.TextFileUtil;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

/**
 * @author YDS
 * @date 2021/2/4
 * @discribe 异常捕捉-app不保活
 */
@SuppressWarnings("all")
class CrashExceptionUtil implements Thread.UncaughtExceptionHandler {
    // 程序的Context对象
    private static Application application;
    private SimpleDateFormat simpleDateFormat;
    // 系统默认的UncaughtException处理类
    private Thread.UncaughtExceptionHandler mDefaultHandler;

    /**
     * 保证只有一个CrashHandler实例
     */
    private CrashExceptionUtil() {
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 获取CrashHandler实例 ,单例模式
     */
    public static CrashExceptionUtil getInstance(Context context) {
        application = (Application) context.getApplicationContext();
        return SafeMode.CRASH_MANAGER_UTIL;
    }

    public static class SafeMode {
        private static final CrashExceptionUtil CRASH_MANAGER_UTIL = new CrashExceptionUtil();
    }

    /**
     * 当UncaughtException发生时会转入该函数来处理
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        StackTraceElement[] traceElements = ex.getStackTrace();
        List<StackTraceElement> stackTraceElements = Arrays.asList(traceElements);
        StringBuffer sb = new StringBuffer();
        sb.append("\n问题原因：" + ex.toString() + "\n");
        for (StackTraceElement stackTraceElement : stackTraceElements) {
            String element = stackTraceElement.toString();
            if (element.contains(application.getPackageName())) {
                sb.append("问题位置：" + element + "\n");
            }
        }

        String exception = sb.toString();
        Log.i("CrashManagerUtil", "Caught Exception : " + exception);
        String innerPath = "/data/data/" + application.getPackageName() + "/files/";
        String fileName = simpleDateFormat.format(System.currentTimeMillis()) + ".txt";
        TextFileUtil.writeTxtToFile(exception, innerPath, fileName);
        mDefaultHandler.uncaughtException(thread, ex);
    }

}