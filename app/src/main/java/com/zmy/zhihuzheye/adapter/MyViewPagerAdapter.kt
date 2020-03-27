package com.zmy.zhihuzheye.adapter


import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter



/**
 *
 * @Description: java类作用描述
 * @Author: zhangmengyun
 * @CreateDate: 2020-03-26 18:13
 * @Notice 无
 */
class MyViewPagerAdapter(fm: FragmentManager, list: List<Fragment>) :
    FragmentStatePagerAdapter(fm) {

      var  mList:List<Fragment>  = list

    override fun getItem(position: Int): Fragment {
       return  mList[position]
    }

    override fun getCount(): Int {
      return  mList.size
    }

}