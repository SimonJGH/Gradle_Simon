package com.yds.gradle_simon

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import com.yds.customize.util.XActivityStack
import com.yds.customize.view.FunctionLoadingDialog

open class BaseActivity : AppCompatActivity() {
    lateinit var loadingDialog: FunctionLoadingDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        QMUIStatusBarHelper.setStatusBarLightMode(this@BaseActivity)
        QMUIStatusBarHelper.setStatusBarDarkMode(this@BaseActivity)

        XActivityStack.getInstance().addActivity(this)
        loadingDialog = FunctionLoadingDialog(this)
    }

    fun startActivity(context: Activity, mclass: Class<*>?) {
        val intent = Intent()
        intent.setClass(context, mclass!!)
        context.startActivity(intent)
    }

    fun startActivity(
        context: Activity,
        mclass: Class<*>?,
        bundle: Bundle?
    ) {
        val intent = Intent()
        intent.putExtras(bundle!!)
        intent.setClass(context, mclass!!)
        context.startActivity(intent)
    }

    /**
     * 无参跳转
     *
     * @param context
     * @param mclass
     */
    open fun startNewActivity(context: Activity, mclass: Class<*>?) {
        val intent = Intent()
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        intent.setClass(context, mclass!!)
        context.startActivity(intent)
    }

    /**
     * 无参跳转
     *
     * @param context
     * @param mclass
     */
    open fun startNewActivity(context: Activity, mclass: Class<*>?, bundle: Bundle?) {
        val intent = Intent()
        intent.putExtras(bundle!!)
        intent.flags = (Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        // intent.flags  Intent.FLAG_ACTIVITY_CLEAR_TOP
        intent.setClass(context, mclass!!)
        context.startActivity(intent)
    }

    /**
     * 判断Activity是否Destroy
     * @param mActivity
     * @return true:已销毁
     */
    @SuppressLint("ObsoleteSdkInt")
    fun isActivityDestroy(mActivity: Activity): Boolean {
        return mActivity == null ||
                mActivity.isFinishing ||
                (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && mActivity.isDestroyed)
    }

}