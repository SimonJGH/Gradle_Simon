package com.yds.gradle_simon.mvp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import com.yds.customize.util.ToastUtil
import com.yds.customize.util.XActivityStack
import com.yds.customize.view.FunctionLoadingDialog

open abstract class BaseActivity<V : BaseContract, P : BasePresenter<V>> : AppCompatActivity() {
    lateinit var loadingDialog: FunctionLoadingDialog
    private var presenter: P? = null
    private var view: V? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (this.presenter == null) {
            this.presenter = initPresenter()
        }

        if (this.view == null) {
            this.view = initView()
        }

        if (this.presenter != null && this.view != null) {
            this.presenter!!.attachView(this.view!!)
        }

//        QMUIStatusBarHelper.setStatusBarLightMode(this@BaseActivity)
        QMUIStatusBarHelper.setStatusBarDarkMode(this@BaseActivity)

        XActivityStack.getInstance().addActivity(this)
        loadingDialog = FunctionLoadingDialog(this)
    }

    abstract fun initView(): V?

    abstract fun initPresenter(): P?

    fun getPresenter(): P? {
        return presenter
    }

    fun showLoading(message: String?) {
        ToastUtil.getInstance().showShortToast(message)

    }

    fun hideLoading() {}

    override fun onDestroy() {
        super.onDestroy()
        if (presenter != null && view != null) {
            presenter!!.detachView()
        }
    }

}