package com.yds.customize.util.crash

/**
 * @author YDS
 * @date 2021/4/7
 * @discribe 异常闪退接口回调
 */
interface CrashExceptionListener {

    fun crashMainException(crashMainMsg:String)

    fun crashThreadException(crashThreadMsg:String)

}