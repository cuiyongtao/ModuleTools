package com.victory.basemodule.tools

import android.content.Context
import android.widget.Toast

/**
 * @author Victory
 * @date 2019/5/24
 * ToastUtil : 吐司工具类
 */
class ToastUtil {
    //短吐司
    fun showToastShort(message: String) {
        if (mToast == null) {
            mToast = Toast.makeText(mContext, message, Toast.LENGTH_SHORT)
        } else {
            mToast!!.setText(message)
            mToast!!.duration = Toast.LENGTH_SHORT
        }
        mToast!!.show()
    }

    //长吐司
    fun showToastLong(message: String) {
        if (mToast == null) {
            mToast = Toast.makeText(mContext, message, Toast.LENGTH_LONG)
        } else {
            mToast!!.setText(message)
            mToast!!.duration = Toast.LENGTH_LONG
        }
        mToast!!.show()
    }

    //单例
    companion object {

        private var mContext: Context? = null

        private var toastUtil: ToastUtil? = null

        private var mToast: Toast? = null

        fun getToastUtilInstance(context: Context): ToastUtil {
            if (toastUtil == null) {
                toastUtil = ToastUtil()
                mContext = context
            }
            return toastUtil!!
        }
    }

}

