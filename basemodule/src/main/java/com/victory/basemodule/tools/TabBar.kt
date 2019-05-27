package com.victory.basemodule.tools

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.victory.basemodule.R

/**
 * @author  Victory
 * @date 2019/5/24
 * BottomBar : 底部导航栏
 */
open class TabBar : LinearLayout {

    constructor(context: Context) : super(context, null) {
        getView()
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet, 0) {
        getView()
    }

    constructor(context: Context, attributeSet: AttributeSet, defstyleattr: Int) : super(context, attributeSet, defstyleattr) {
        getView()
    }

    private fun getView(): View {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_tab_bar, this)
        return view
    }



}