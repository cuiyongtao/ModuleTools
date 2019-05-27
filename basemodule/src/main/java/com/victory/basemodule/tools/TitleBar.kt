package com.victory.basemodule.tools

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar
import com.victory.basemodule.R

/**
 * @author  Victory
 * @date 2019/5/24
 * TitleBar : 顶部导航栏
 */
class TitleBar : Toolbar {
    private var mTxtLeftInterface: TxtLeftInterface? = null
    fun setTxtLeftInterFace(txtLeftInterface: TxtLeftInterface) {
        mTxtLeftInterface = txtLeftInterface;
    }

    constructor(context: Context) : super(context) {
        getView()
        init()
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet, 0) {
        getView()
        init()
    }

    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(context, attributeSet, 0) {
        getView()
        init()
    }

    private fun getView(): View {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_title_bar, this)
        return view
    }

    private fun init() {

    }

    /**
     * 左边TextView
     */

    fun txtLeftInit() {
        val txtLeft = getView().findViewById<TextView>(R.id.txtLeft)
        txtLeft.setOnClickListener {
            if (mTxtLeftInterface != null) {
                mTxtLeftInterface!!.txtLeftOnclick();
            }
        }
    }

    interface TxtLeftInterface {
        public fun txtLeftOnclick();
    }

}
