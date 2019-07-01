package com.victory.basemodule.dialog

import android.content.Context
import android.os.Bundle
import android.view.WindowManager

import androidx.appcompat.app.AlertDialog

import com.victory.basemodule.R
import com.victory.basemodule.application.BaseApplication.Companion.mContext

/**
 * @author Victory
 * @date 2019/6/14
 * CommonDialog :
 */
class CommonDialog : AlertDialog(mContext, R.style.MyDialog) {

    override fun onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_common)
        init()
    }

    fun init() {
        val windowManager = mContext.getSystemService(Context
                .WINDOW_SERVICE) as WindowManager
        // 将对话框的大小按屏幕大小的百分比设置
        val display = windowManager.defaultDisplay
        val lp = window!!.attributes
        lp.width = (display.width * 0.8).toInt()
        lp.height = (display.height * 0.6).toInt()
        window!!.attributes = lp
        setCancelable(false)
    }
}
