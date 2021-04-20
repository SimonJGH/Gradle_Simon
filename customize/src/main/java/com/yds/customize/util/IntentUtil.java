package com.yds.customize.util;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * @author YDS
 * @date 2021/4/20
 * @discribe 跳转工具  activity显示、隐式跳转
 */
@SuppressWarnings("all")
public class IntentUtil {
    /**
     * 显示跳转 - 无参
     */
    public static void startActivity(Context context, Class clazz) {
        Intent intent = new Intent();
        intent.setClass(context, clazz);
        context.startActivity(intent);
    }

    /**
     * 隐式跳转 - 无参
     */
    public static void startActivity(Context context, String action) {
        Intent intent = new Intent();
        intent.setAction(action);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        context.startActivity(intent);
    }

    /**
     * 显示跳转 - 有参
     */
    public static void startActivity(Context context, Class clazz, Bundle bundle) {
        Intent intent = new Intent();
        intent.putExtras(bundle);
        intent.setClass(context, clazz);
        context.startActivity(intent);
    }

    /**
     * 隐式跳转 - 有参
     */
    public static void startActivity(Context context, String action, Bundle bundle) {
        Intent intent = new Intent();
        intent.putExtras(bundle);
        intent.setAction(action);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        context.startActivity(intent);
    }

    /**
     * 开启新栈 - 显示跳转
     */
    public static void startNewTaskActivity(Context context, Class clazz) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setClass(context, clazz);
        context.startActivity(intent);
    }

    /**
     * 开启新栈 - 隐式跳转
     */
    public static void startNewTaskActivity(Context context, String action) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(action);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        context.startActivity(intent);
    }

}
