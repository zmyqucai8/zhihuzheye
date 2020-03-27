package com.zmy.zhihuzheye.ui.home

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

import com.zmy.zhihuzheye.R

import com.zmy.zhihuzheye.adapter.MyViewPagerAdapter

import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    lateinit var root: View
    lateinit var mViewPagerAdapter: MyViewPagerAdapter
    lateinit var mViewPager: ViewPager
    lateinit var mTabLayout: TabLayout


    companion object{

     fun newInstance(): Fragment{
        val myFragment: HomeFragment= HomeFragment()
        val bundle = Bundle()
        myFragment.setArguments(bundle)
        return myFragment
    }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        root = inflater.inflate(R.layout.fragment_home, container, false)

        initView()
        initData()
        return root
    }

    private fun initData() {

    }


    /**
     * 初始化VIEW
     */
    private fun initView() {
        mViewPager = root.findViewById(R.id.viewPager)
        mViewPager.offscreenPageLimit=10
        mViewPagerAdapter = MyViewPagerAdapter(
            activity!!.supportFragmentManager,
            listOf(
                NewsFragment("top"),
                NewsFragment("shehui"),
                NewsFragment("guonei"),
                NewsFragment("guoji"),
                NewsFragment("yule"),
                NewsFragment("tiyu"),
                NewsFragment("junshi"),
                NewsFragment("keji"),
                NewsFragment("caijing"),
                NewsFragment("shishang")
            )
        )

        mViewPager.adapter = mViewPagerAdapter


        //tablayout
        mTabLayout = root.findViewById(R.id.tabLayout)
        mTabLayout.addTab(mTabLayout.newTab().setText("头条"))
        mTabLayout.addTab(mTabLayout.newTab().setText("社会"))
        mTabLayout.addTab(mTabLayout.newTab().setText("国内"))
        mTabLayout.addTab(mTabLayout.newTab().setText("国际"))
        mTabLayout.addTab(mTabLayout.newTab().setText("娱乐"))
        mTabLayout.addTab(mTabLayout.newTab().setText("体育"))
        mTabLayout.addTab(mTabLayout.newTab().setText("军事"))
        mTabLayout.addTab(mTabLayout.newTab().setText("科技"))
        mTabLayout.addTab(mTabLayout.newTab().setText("财经"))
        mTabLayout.addTab(mTabLayout.newTab().setText("时尚"))

        mTabLayout.setTabTextColors(Color.BLACK,Color.YELLOW)


        mTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                val position = tab!!.position
                viewPager.currentItem = position
            }
        })

        mViewPager.addOnPageChangeListener(object :
            TabLayout.TabLayoutOnPageChangeListener(mTabLayout) {})
    }


}