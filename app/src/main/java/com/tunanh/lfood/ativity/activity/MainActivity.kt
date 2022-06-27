package com.tunanh.lfood.ativity.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.tunanh.lfood.R
import com.tunanh.lfood.ativity.adapter.FragmentMainAdapter
import com.tunanh.lfood.ativity.fragment.Category
import com.tunanh.lfood.ativity.fragment.Home
import com.tunanh.lfood.ativity.fragment.More
import com.tunanh.lfood.ativity.fragment.WishList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var tabLayout= findViewById<TabLayout>(R.id.tabLayout)
        var viewPager= findViewById<ViewPager>(R.id.viewpagermain)

        tabLayout.setupWithViewPager(viewPager)

        var fmmainadapter=FragmentMainAdapter(supportFragmentManager,FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        fmmainadapter.addFragment(Home(),"Home")
        fmmainadapter.addFragment(Category(),"Category")
        fmmainadapter.addFragment(WishList(),"WishList")
        fmmainadapter.addFragment(More(),"More")
        viewPager.adapter=fmmainadapter


    }
}