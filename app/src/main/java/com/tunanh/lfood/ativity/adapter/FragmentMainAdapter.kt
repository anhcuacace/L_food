package com.tunanh.lfood.ativity.adapter

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.tunanh.lfood.ativity.fragment.Category
import com.tunanh.lfood.ativity.fragment.Home
import com.tunanh.lfood.ativity.fragment.More
import com.tunanh.lfood.ativity.fragment.WishList

class FragmentMainAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> Home()
            1 -> Category()
            2 -> WishList()
            3 -> More()
            else -> {
                throw Resources.NotFoundException("position not found")}
        }
    }
}