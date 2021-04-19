package com.yds.gradle_simon.mvp

/**
 * @author YDS
 * @date 2021/4/13
 * @discribe mvp基类view
 */
interface BaseContract {

    fun showLoading(msg: String?)

    fun hideLoading()

}