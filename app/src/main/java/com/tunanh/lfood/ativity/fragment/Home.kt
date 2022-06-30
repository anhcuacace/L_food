package com.tunanh.lfood.ativity.fragment

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.tunanh.lfood.R
import com.tunanh.lfood.ativity.Item.SliderItem
import com.tunanh.lfood.ativity.adapter.SliderAdapter
import me.relex.circleindicator.CircleIndicator3


class Home : Fragment() {

    val handler=Handler()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        var viewPager= view.findViewById<ViewPager2>(R.id.slide_home)
        var sliderItem:MutableList<SliderItem> = ArrayList()
        sliderItem.add(SliderItem(R.drawable.slider1))
        sliderItem.add(SliderItem(R.drawable.slider2))
        sliderItem.add(SliderItem(R.drawable.slider3))
        sliderItem.add(SliderItem(R.drawable.slider4))

        viewPager.adapter =SliderAdapter(sliderItem,viewPager)
        var indecator= view.findViewById<CircleIndicator3>(R.id.CircleIndicator3_slide)
        indecator.setViewPager(viewPager)

//        viewPager.clipToPadding=false
//        viewPager.clipChildren=false
//        viewPager.offscreenPageLimit=3
//        viewPager.getChildAt(0).overScrollMode= RecyclerView.OVER_SCROLL_NEVER

//        var compositePageTransformer= CompositePageTransformer()
//        compositePageTransformer.addTransformer(MarginPageTransformer(30))
//        compositePageTransformer.addTransformer { page, position ->
//            var r=1- abs(position)
//            page.scaleY= 0.85f+r*0.25f
//
//        }
//        viewPager2.setPageTransformer(compositePageTransformer)
        var runnable= Runnable {
            viewPager.currentItem=viewPager.currentItem+1
        }
        viewPager.registerOnPageChangeCallback(object :ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnable)

                handler.postDelayed(runnable,3000)
            }

        })


        return view
    }

    override fun onPause() {
        super.onPause()
    }



}