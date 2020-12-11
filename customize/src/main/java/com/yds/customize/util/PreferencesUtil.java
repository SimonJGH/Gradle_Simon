package com.yds.customize.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 作者：${Simon} on 2016/11/22 0022 17:29
 * <p>
 * 描述：PreferencesUtils
 */
@SuppressWarnings("all")
public class PreferencesUtil extends BaseCustomizeUtil {

    public static String PREFERENCE_NAME = "YDSimon";

    private PreferencesUtil() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * put string preferences
     *
     * @param context
     * @param key
     * @param value
     */
    public static boolean putString(String key, String value) {
        SharedPreferences settings =  mContext.getSharedPreferences(
                PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        return editor.commit();
    }

    /**
     * get string preferences
     *
     * @param context
     * @param key
     */
    public static String getString(String key) {
        return getString( key, null);
    }

    /**
     * get string preferences
     *
     * @param context
     * @param key
     * @param defaultValue
     */
    public static String getString(String key, String defaultValue) {
        SharedPreferences settings =  mContext.getSharedPreferences(
                PREFERENCE_NAME, Context.MODE_PRIVATE);
        return settings.getString(key, defaultValue);
    }

    /**
     * put int preferences
     *
     * @param context
     * @param key
     * @param value
     */
    public static boolean putInt(String key, int value) {
        SharedPreferences settings =  mContext.getSharedPreferences(
                PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);
        return editor.commit();
    }

    /**
     * get int preferences
     *
     * @param context
     * @param key
     */
    public static int getInt( String key) {
        return getInt( key, -1);
    }

    /**
     * get int preferences
     *
     * @param context
     * @param key
     * @param defaultValue
     */
    public static int getInt(String key, int defaultValue) {
        if ( mContext != null) {
            SharedPreferences settings =  mContext.getSharedPreferences(
                    PREFERENCE_NAME, Context.MODE_PRIVATE);
            return settings.getInt(key, defaultValue);
        }
        return 0;
    }

    /**
     * put long preferences
     *
     * @param context
     * @param key
     */
    public static boolean putLong(String key, long value) {
        SharedPreferences settings =  mContext.getSharedPreferences(
                PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putLong(key, value);
        return editor.commit();
    }

    /**
     * get long preferences
     *
     * @param context
     * @param key
     */
    public static long getLong(String key) {
        return getLong(key, -1);
    }

    /**
     * get long preferences
     *
     * @param context
     * @param key
     * @param defaultValue
     */
    public static long getLong(String key, long defaultValue) {
        SharedPreferences settings =  mContext.getSharedPreferences(
                PREFERENCE_NAME, Context.MODE_PRIVATE);
        return settings.getLong(key, defaultValue);
    }

    /**
     * put float preferences
     *
     * @param context
     * @param key
     * @param value
     */
    public static boolean putFloat(String key, float value) {
        SharedPreferences settings =  mContext.getSharedPreferences(
                PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putFloat(key, value);
        return editor.commit();
    }

    /**
     * get float preferences
     *
     * @param context
     * @param key
     */
    public static float getFloat(String key) {
        return getFloat( key, -1);
    }

    /**
     * get float preferences
     *
     * @param context
     * @param key
     * @param defaultValue
     */
    public static float getFloat(String key, float defaultValue) {
        SharedPreferences settings = mContext.getSharedPreferences(
                PREFERENCE_NAME, Context.MODE_PRIVATE);
        return settings.getFloat(key, defaultValue);
    }

    /**
     * put boolean preferences
     *
     * @param context
     * @param key
     * @param value
     */
    public static boolean putBoolean(String key, boolean value) {
        SharedPreferences settings = mContext.getSharedPreferences(
                PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        return editor.commit();
    }

    /**
     * get boolean preferences, default is false
     *
     * @param context
     * @param key
     */
    public static boolean getBoolean( String key) {
        return getBoolean( key, false);
    }

    /**
     * get boolean preferences
     *
     * @param context
     * @param key
     * @param defaultValue
     */
    public static boolean getBoolean( String key,
                                     boolean defaultValue) {
        SharedPreferences settings =  mContext.getSharedPreferences(
                PREFERENCE_NAME, Context.MODE_PRIVATE);
        return settings.getBoolean(key, defaultValue);
    }

    /**
     * 保存list
     *
     * @param context
     * @param tag
     * @param list
     * @return
     */
    public static boolean saveList(Context context, String tag,
                                   List<String> list) {
        SharedPreferences settings = context.getSharedPreferences(
                PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(tag + "_size", list.size()); /* sKey is an array */

        for (int i = 0; i < list.size(); i++) {
            editor.remove(tag + "_" + i);
            editor.putString(tag + "_" + i, list.get(i));
        }
        return editor.commit();
    }

    /**
     * 取出list
     *
     * @param context
     * @param tag
     * @return
     */
    public static List<String> loadList(Context context, String tag) {
        SharedPreferences settings = context.getSharedPreferences(
                PREFERENCE_NAME, Context.MODE_PRIVATE);
        int size = settings.getInt(tag + "_size", 0);
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < size; i++) {
            list.add(settings.getString(tag + "_" + i, null));
        }
        return list;
    }

    /**
     * 保存arraylist
     *
     * @param context
     * @param tag
     * @param arrayList
     * @return
     */
    public static boolean saveArrayList(Context context, String tag,
                                        ArrayList<String> arrayList) {
        SharedPreferences settings = context.getSharedPreferences(
                PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(tag + "_size", arrayList.size()); /* sKey is an array */

        for (int i = 0; i < arrayList.size(); i++) {
            editor.remove(tag + "_" + i);
            editor.putString(tag + "_" + i, arrayList.get(i));
        }
        return editor.commit();
    }

    /**
     * 取出arrylist
     *
     * @param context
     * @param tag
     * @return
     */
    public static ArrayList<String> loadArrayList(Context context, String tag) {
        SharedPreferences settings = context.getSharedPreferences(
                PREFERENCE_NAME, Context.MODE_PRIVATE);
        int size = settings.getInt(tag + "_size", 0);
        ArrayList<String> arrayList = new ArrayList<String>();
        for (int i = 0; i < size; i++) {
            arrayList.add(settings.getString(tag + "_" + i, null));
        }
        return arrayList;
    }

    /**
     * 查询某个key是否已经存在
     *
     * @param context
     * @param key
     * @return
     */
    public static boolean contains(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE_NAME,
                Context.MODE_PRIVATE);
        return sp.contains(key);
    }

    /**
     * 返回所有的键值对
     *
     * @param context
     * @return
     */
    public static Map<String, ?> getAll(Context context) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE_NAME,
                Context.MODE_PRIVATE);
        return sp.getAll();
    }

    /**
     * 移除某个key值已经对应的值
     *
     * @param context
     * @param key
     */
    public static void remove(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 清空SP
     *
     * @param context
     * @return
     */
    public static boolean clearSharedPreference(Context context) {
        SharedPreferences settings = context.getSharedPreferences(
                PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.clear();
        return editor.commit();
    }

    /**
     * 创建一个解决SharedPreferencesCompat.apply方法的一个兼容类
     */
    private static class SharedPreferencesCompat {
        private static final Method sApplyMethod = findApplyMethod();

        /**
         * 反射查找apply的方法
         *
         * @return
         */
        @SuppressWarnings({"unchecked", "rawtypes"})
        private static Method findApplyMethod() {
            try {
                Class clz = SharedPreferences.Editor.class;
                return clz.getMethod("apply");
            } catch (NoSuchMethodException e) {
            }

            return null;
        }

        /**
         * 如果找到则使用apply执行，否则使用commit
         *
         * @param editor
         */
        public static void apply(SharedPreferences.Editor editor) {
            try {
                if (sApplyMethod != null) {
                    sApplyMethod.invoke(editor);
                    return;
                }
            } catch (IllegalArgumentException e) {
            } catch (IllegalAccessException e) {
            } catch (InvocationTargetException e) {
            }
            editor.commit();
        }
    }
}
