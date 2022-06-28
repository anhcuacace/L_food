package com.tunanh.lfood.ativity.activity

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.tunanh.lfood.R

import com.tunanh.lfood.ativity.adapter.FragmentMainAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var tabLayout= findViewById<TabLayout>(R.id.tabLayout)
        var viewPager= findViewById<ViewPager2>(R.id.viewpagermain)

//        tabLayout.setupWithViewPager(viewPager)

//        var fmmainadapter=FragmentMainAdapter(supportFragmentManager,FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
//        fmmainadapter.addFragment(Home(),"Home")
//        fmmainadapter.addFragment(Category(),"Category")
//        fmmainadapter.addFragment(WishList(),"WishList")
//        fmmainadapter.addFragment(More(),"More")
//        viewPager.adapter=fmmainadapter
        viewPager.adapter=FragmentMainAdapter(this)
        TabLayoutMediator(tabLayout,viewPager){tab,index ->
            tab.text=when(index){
                0->{"home"}
                1->{"category"}
                2->{"wish list"}
                3->{"more"}
                else->{throw Resources.NotFoundException("position not found")}
            }
        }.attach()

    }
}