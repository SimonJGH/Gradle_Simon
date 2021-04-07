package com.yds.customize.util.crash

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import java.util.*

/**
 * @author YDS
 * @date 2021/4/7
 * @discribe 异常捕获工具类
 */
object CrashExceptionHelper {

    /**
     * 捕捉主线程异常
     */
    fun caughtException(context: Context, crashExceptionListener: CrashExceptionListener) {
        var aaa = ""
        //主线程异常捕捉
        val mHandler = Handler(Looper.getMainLooper())
        mHandler.post {
            while (true) {
                try {
                    Looper.loop()
                } catch (e: Exception) {
                    val traceElements = e.stackTrace
                    val stackTraceElements = Arrays.asList(*traceElements)
                    val sb = StringBuffer()
                    sb.append("\n问题原因：$e\n")
                    for (stackTraceElement in stackTraceElements) {
                        val element = stackTraceElement.toString()
                        if (element.contains(context.packageName, ignoreCase = false)) {
                            sb.append("问题位置：$element\n")
                        }
                    }
                    crashExceptionListener.crashMainException(sb.toString())
                    Log.i("CrashUtil", "Caught Exception Main Thread : $sb")
                }
            }
        }

        //子线程异常捕捉
        Thread.setDefaultUncaughtExceptionHandler(CrashThreadUtil(context, crashExceptionListener))
    }
}