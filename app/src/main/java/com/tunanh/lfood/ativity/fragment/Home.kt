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
import com.tunanh.lfood.ativity.adapter.HotdealAdapter
import com.tunanh.lfood.ativity.adapter.SliderAdapter
import com.tunanh.lfood.ativity.data.CategoryData
import com.tunanh.lfood.ativity.data.itemfood
import com.tunanh.lfood.ativity.item.CategoryItem
import com.tunanh.lfood.ativity.item.SliderItem
import com.tunanh.lfood.ativity.item.itemFood
import me.relex.circleindicator.CircleIndicator3


class Home : Fragment() {

    var handler = Handler()
//    var runnable: Runnable?=null
    var viewPager:ViewPager2?=null
    private var categoryData = CategoryData()
    private var itemfood = com.tunanh.lfood.ativity.data.itemfood()
    private var img = categoryData.img
    private var name = categoryData.name
    val runnable = Runnable {
        if (viewPager!!.currentItem == AddsliderItem().size - 1) {
            viewPager!!.currentItem = 0
        } else {
            viewPager!!.currentItem = viewPager!!.currentItem + 1
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        //set viewpager
        viewPager = view.findViewById<ViewPager2>(R.id.slide_home)

        var indecator = view.findViewById<CircleIndicator3>(R.id.CircleIndicator3_slide)

        var sliderAdapter = SliderAdapter(AddsliderItem(),)

        viewPager!!.adapter = sliderAdapter

        indecator.setViewPager(viewPager)
//        sliderAdapter.registerAdapterDataObserver(indecator.adapterDataObserver)
        //set recyclerview category

        var recyclerViewCategory = view.findViewById<RecyclerView>(R.id.rcl_category_home)
        recyclerViewCategory.isNestedScrollingEnabled=false
        recyclerViewCategory.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewCategory.setHasFixedSize(true)
        recyclerViewCategory.adapter = CategoryrclvAdapter(setDataCategoryList())
//set recyclerview hot deal
        var recyclerViewHotdeal = view.findViewById<RecyclerView>(R.id.rcl_hotdealfood_home)
        recyclerViewHotdeal.isNestedScrollingEnabled=false
        recyclerViewHotdeal.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewHotdeal.setHasFixedSize(false)
        recyclerViewHotdeal.adapter = HotdealAdapter(setDataHotDealList())


        viewPager!!.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnable!!)

                handler.postDelayed(runnable!!, 3000)
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

    private fun setDataHotDealList(): ArrayList<itemFood> {
        var arrayList: ArrayList<itemFood> = ArrayList()

        val saleOff = itemfood.saleOff
        val imgFood = itemfood.imgFood
        val distance = itemfood.distance
        val name = itemfood.name
        val rating = itemfood.ratting
        for (i in 0 until 10) {
            if (i % 4 == 0) {
                arrayList.add(
                    itemFood(
                        saleOff[0],
                        imgFood[0],
                        resources.getString(distance[0]) + resources.getString(distance[2]),
                        resources.getString(name[0]),
                        resources.getString(rating[0]) + resources.getString(rating[2])
                    )
                )
            } else if ((i - 1) % 4 == 0) {
                arrayList.add(
                    itemFood(
                        saleOff[1],
                        imgFood[1],
                        resources.getString(distance[0]) + resources.getString(distance[3]),
                        resources.getString(name[1]),
                        resources.getString(rating[0]) + resources.getString(rating[3])
                    )
                )
            } else if ((i - 2) % 4 == 0) {
                arrayList.add(
                    itemFood(
                        saleOff[0],
                        imgFood[0],
                        resources.getString(distance[1]) + resources.getString(distance[2]),
                        resources.getString(name[0]),
                        resources.getString(rating[1]) + resources.getString(rating[2])
                    )
                )
            } else if ((i - 3) % 4 == 0) {
                arrayList.add(
                    itemFood(
                        saleOff[1],
                        imgFood[1],
                        resources.getString(distance[1]) + resources.getString(distance[3]),
                        resources.getString(name[1]),
                        resources.getString(rating[1]) + resources.getString(rating[3])
                    )
                )
            }
        }


        return arrayList
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

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(runnable,3000)
    }
}



