package com.tunanh.lfood.ativity.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.tunanh.lfood.ativity.fragment.Category
import com.tunanh.lfood.ativity.fragment.Home
import com.tunanh.lfood.ativity.fragment.More
import com.tunanh.lfood.ativity.fragment.WishList

class FragmentMainAdapter1(fragmentActivity: FragmentStateAdapter): FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        when(position){
            0 -> Home()
            1 -> Category()
            2 -> WishList()
            3 -> More()
        }
    }
}