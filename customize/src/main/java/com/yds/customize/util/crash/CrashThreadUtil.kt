package com.yds.customize.util.crash

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.yds.customize.util.crash.CrashExceptionListener
import java.util.*

/**
 * @author YDS
 * @date 2020/9/7
 * @discribe 捕捉子线程异常
 */
class CrashThreadUtil : Thread.UncaughtExceptionHandler {
    private var mCrashExceptionListener: CrashExceptionListener? = null
    private var mContext: Context? = null

    constructor(context: Context, crashExceptionListener: CrashExceptionListener) {
        this.mContext = context
        this.mCrashExceptionListener = crashExceptionListener
    }

    override fun uncaughtException(t: Thread, e: Throwable) {
        val mHandler = Handler(Looper.getMainLooper())
        mHandler.post {
            val traceElements = e.stackTrace
            val stackTraceElements = Arrays.asList(*traceElements)
            val sb = StringBuffer()
            sb.append("\n问题原因：$e\n")
            for (stackTraceElement in stackTraceElements) {
                val element = stackTraceElement.toString()
                if (element.contains(mContext!!.packageName)) {
                    sb.append("问题位置：$element\n")
                }
            }
            mCrashExceptionListener?.crashThreadException(sb.toString())
            Log.i("CrashUtil", "Caught Exception Other Thread : $sb")
        }
    }

}