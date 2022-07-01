package com.tunanh.lfood.ativity.fragment

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.tunanh.lfood.R
import com.tunanh.lfood.ativity.adapter.CategoryrclvAdapter
import com.tunanh.lfood.ativity.adapter.SliderAdapter
import com.tunanh.lfood.ativity.data.CategoryData
import com.tunanh.lfood.ativity.item.CategoryItem
import com.tunanh.lfood.ativity.item.SliderItem
import me.relex.circleindicator.CircleIndicator3


class Home : Fragment() {

    val handler = Handler()
    private var categoryData = CategoryData()
    private var img = categoryData.img
    private var name = categoryData.name


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        //set viewpager
        val viewPager = view.findViewById<ViewPager2>(R.id.slide_home)

        var indecator = view.findViewById<CircleIndicator3>(R.id.CircleIndicator3_slide)

        var sliderAdapter = SliderAdapter(AddsliderItem(), viewPager)

        viewPager.adapter = sliderAdapter

        indecator.setViewPager(viewPager)
//        sliderAdapter.registerAdapterDataObserver(indecator.adapterDataObserver)
        //set recyclerview category
        var recyclerViewCategory = view.findViewById<RecyclerView>(R.id.rcl_category_home)
        recyclerViewCategory.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewCategory.setHasFixedSize(true)
        recyclerViewCategory.adapter = CategoryrclvAdapter(setDataCategoryList())
//set recyclerview hot deal
        var recyclerViewHotdeal=

        var runnable = Runnable {
            viewPager.currentItem = viewPager.currentItem + 1
        }
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnable)

                handler.postDelayed(runnable, 3000)
            }

        })


        return view
    }

    private fun AddsliderItem(): ArrayList<SliderItem> {
        var sliderItem: ArrayList<SliderItem> = ArrayList()
        sliderItem.add(SliderItem(R.drawable.slider1))
        sliderItem.add(SliderItem(R.drawable.slider2))
        sliderItem.add(SliderItem(R.drawable.slider3))
        sliderItem.add(SliderItem(R.drawable.slider4))
        return sliderItem

    }

    private fun setDataCategoryList(): ArrayList<CategoryItem> {
        var arrayList: ArrayList<CategoryItem> = ArrayList()

        val imgs = categoryData.img
        val names = categoryData.name
        for (i in 0 until imgs.size) {
            arrayList.add(CategoryItem(imgs[i], resources.getString(names[i])))
        }


        return arrayList
    }


}