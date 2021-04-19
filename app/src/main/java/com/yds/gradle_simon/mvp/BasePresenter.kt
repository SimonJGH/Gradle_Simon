package com.yds.gradle_simon.mvp

/**
 * @author YDS
 * @date 2021/4/13
 * @discribe mvp基类交互
 */
open class BasePresenter<V : BaseContract?> {
    var view: V? = null

    fun attachView(v: V) {
        if (view == null) {
            view = v
        }
    }

    fun detachView() {
        if (view != null) {
            view = null
        }
    }
}