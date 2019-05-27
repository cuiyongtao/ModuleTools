package com.victory.basemodule.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * @author  Victory
 * @date 2019/5/27
 * FragmentViewPagerAdapter :
 */
class FragmentViewPagerAdapter(fragmentManager: FragmentManager, fragmentList: List<Fragment>) : FragmentPagerAdapter(fragmentManager) {
    var mList = fragmentList;
    override fun getCount(): Int {
        return mList.size
    }

    override fun getItem(position: Int): Fragment {
        return mList[position]
    }
}