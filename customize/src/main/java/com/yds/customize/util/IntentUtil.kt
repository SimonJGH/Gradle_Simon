package com.yds.customize.util

import android.content.Context
import android.content.Intent
import android.os.Bundle

/**
 * @author YDS
 * @date 2021/4/14
 * @discribe activity显示、隐式跳转
 */
object IntentUtil {
    /**
     * 显示跳转 - 无参
     */
    fun startActivity(context: Context, clazz: Class<*>) {
        val intent = Intent()
        intent.setClass(context, clazz)
        context.startActivity(intent)
    }

    /**
     * 隐式跳转 - 无参
     */
    fun startActivity(context: Context, action: String) {
        val intent = Intent()
        intent.action = action
        intent.addCategory(Intent.CATEGORY_DEFAULT)
        context.startActivity(intent)
    }

    /**
     * 显示跳转 - 有参
     */
    fun startActivity(context: Context, clazz: Class<*>, bundle: Bundle) {
        val intent = Intent()
        intent.putExtras(bundle)
        intent.setClass(context, clazz)
        context.startActivity(intent)
    }

    /**
     * 隐式跳转 - 有参
     */
    fun startActivity(context: Context, action: String, bundle: Bundle) {
        val intent = Intent()
        intent.putExtras(bundle)
        intent.action = action
        intent.addCategory(Intent.CATEGORY_DEFAULT)
        context.startActivity(intent)
    }

    /**
     * 开启新栈 - 显示跳转
     */
    fun startNewTaskActivity(context: Context, clazz: Class<*>) {
        val intent = Intent()
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.setClass(context, clazz)
        context.startActivity(intent)
    }

    /**
     * 开启新栈 - 隐式跳转
     */
    fun startNewTaskActivity(context: Context, action: String) {
        val intent = Intent()
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.action = action
        intent.addCategory(Intent.CATEGORY_DEFAULT)
        context.startActivity(intent)
    }

}