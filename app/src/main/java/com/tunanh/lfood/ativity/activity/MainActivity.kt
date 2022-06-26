package com.tunanh.lfood.ativity.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.tunanh.lfood.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var tabLayout= findViewById<TabLayout>(R.id.tabLayout)
        var viewPager2= findViewById<ViewPager2>(R.id.viewpagermain)


    }
}