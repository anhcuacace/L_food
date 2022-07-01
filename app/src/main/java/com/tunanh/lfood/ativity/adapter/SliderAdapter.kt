package com.tunanh.lfood.ativity.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

import androidx.viewpager2.widget.ViewPager2
import com.tunanh.lfood.R

import com.tunanh.lfood.ativity.item.SliderItem

class SliderAdapter(
    val sliderItem: ArrayList<SliderItem>, val viewPager: ViewPager2

) : RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {
    inner class SliderViewHolder(var v: View) : RecyclerView.ViewHolder(v) {
        var img = v.findViewById<ImageView>(R.id.img_slide)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.slide_item, parent, false)
        return SliderViewHolder(v)
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        val listimg = sliderItem[position]
        holder.img.setImageResource(listimg.img)
        if (position == sliderItem.size - 1) {
            viewPager.post(run)
        }

    }

    val run = Runnable {
        sliderItem.addAll(sliderItem)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = sliderItem.size
}