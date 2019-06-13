package com.victory.basemodule.tools

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.fragment.app.Fragment
import com.victory.basemodule.R
import com.victory.basemodule.adapter.FragmentViewPagerAdapter

/**
 * @author  Victory
 * @date 2019/5/24
 * BottomBar : 底部导航栏
 */
open class TabBar : LinearLayout {

    lateinit var fragmentViewPagerAdapter: FragmentViewPagerAdapter

    //
    lateinit var firstFragment: AppCompatRadioButton
    lateinit var secondFragment: AppCompatRadioButton
    lateinit var thirdFragment: AppCompatRadioButton
    lateinit var fourthFragment: AppCompatRadioButton
    lateinit var fifthFragment: AppCompatRadioButton

    constructor(context: Context) : super(context, null) {
        init()
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet, 0) {
        init()
    }

    constructor(context: Context, attributeSet: AttributeSet, defstyleattr: Int) : super(context, attributeSet, defstyleattr) {
        init()
    }

    private fun getView(): View {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_tab_bar, this)
        return view
    }

    private fun init() {
        firstFragment = getView().findViewById(R.id.firstFragment)
        secondFragment = getView().findViewById(R.id.secondFragment)
        thirdFragment = getView().findViewById(R.id.thirdFragment)
        fourthFragment = getView().findViewById(R.id.fourthFragment)
        fifthFragment = getView().findViewById(R.id.fifthFragment)
        mViewOnClick()
    }


    private fun mViewOnClick(){
        firstFragment.setOnClickListener {
            Toast.makeText(context,"dfa",Toast.LENGTH_SHORT).show()
        }
    }

    fun setFragmentList(fragmetList: List<Fragment>) :List<Fragment> {
        return fragmetList;
    }

    private fun setAdapter(){
    }
}