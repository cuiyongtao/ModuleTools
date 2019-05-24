package com.victory.basemodule.tools

import android.util.Log

/**
 * @author  Victory
 * @date 2019/5/24
 * LogUtil : 日志工具类
 */
class LogUtil {

    companion object {
        private var logUtil: LogUtil? = null
        private var tag: String? = null

        fun getLogUtil(tag: String): LogUtil {
            if (logUtil == null) {
                logUtil = LogUtil()
            }
            this.tag = tag;
            return logUtil!!
        }
    }

    /**
     * info 日志
     */
    fun getLogI(message: String) {
        Log.i(tag, message)
    }

    /**
     * debug 日志
     */
    fun getLogD(message: String) {
        Log.d(tag, message)
    }

    /**
     * error 日志
     */
    fun getLogE(message: String) {
        Log.e(tag, message)
    }

    /**
     * 网络请求打印，主要tag区分：地址，参数，返回值等
     */
    fun getLogNet(tag: String, message: String) {
        Log.e(tag, message)
    }
}