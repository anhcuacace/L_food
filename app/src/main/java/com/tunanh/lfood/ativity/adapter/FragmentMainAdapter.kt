package com.tunanh.lfood.ativity.adapter


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class FragmentMainAdapter(fm: FragmentManager, behavior: Int) : FragmentPagerAdapter(fm, behavior) {
    private val fragmentArrayList= mutableListOf<Fragment>()
    private val fragmentTitle= mutableListOf<String>()
    override fun getCount(): Int {
        return fragmentArrayList.size
    }

    override fun getItem(position: Int): Fragment {
        return fragmentArrayList.get(position)
    }
    public fun addFragment(fragment: Fragment,tiTle:String){
        fragmentArrayList.add(fragment)
        fragmentTitle.add(tiTle)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentTitle.get(position)
    }
}