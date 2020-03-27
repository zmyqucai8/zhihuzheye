package com.zmy.zhihuzheye

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.blankj.utilcode.util.FragmentUtils
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import com.zmy.zhihuzheye.ui.dashboard.DashboardFragment
import com.zmy.zhihuzheye.ui.home.HomeFragment
import com.zmy.zhihuzheye.ui.notifications.NotificationsFragment
import java.lang.reflect.Array.newInstance


class MainActivity : AppCompatActivity() {



    var mFragments: MutableList<Fragment> = ArrayList()
    var curIndex = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.labelVisibilityMode = LabelVisibilityMode.LABEL_VISIBILITY_LABELED
        navView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_home -> showCurrentFragment(0)
                R.id.navigation_dashboard -> showCurrentFragment(1)
                R.id.navigation_notifications -> showCurrentFragment(2)

            }
            true
        }




        mFragments.add(HomeFragment.newInstance())
        mFragments.add(DashboardFragment.newInstance())
        mFragments.add(NotificationsFragment.newInstance())


        FragmentUtils.add(
            supportFragmentManager,
            mFragments,
            R.id.fragmentContainer,
            0
        )

    }
    private fun showCurrentFragment(index: Int) {
        curIndex = index
        FragmentUtils.showHide(index, mFragments)
    }
}
