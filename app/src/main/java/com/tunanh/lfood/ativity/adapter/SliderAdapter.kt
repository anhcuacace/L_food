package com.tunanh.lfood.ativity.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.tunanh.lfood.R
import com.tunanh.lfood.ativity.Item.SliderItem

class SliderAdapter public constructor(
    sliderItem: MutableList<SliderItem>,
    viewPager: ViewPager2
): RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {

    private var sliderItem: List<SliderItem>
    private var viewPager:ViewPager2
    init {
        this.viewPager=viewPager
        this.sliderItem=sliderItem
    }
    class SliderViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        private var imageView=itemView.findViewById<ImageView>(R.id.img_slide)

        fun image(sliderItem: SliderItem){
            imageView.setImageResource(sliderItem.img)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        return SliderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.slide_item,parent,false))
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.image(sliderItem[position])
//        holder.itemView.setOnClickListener()
        if(position==sliderItem.size-1){
            viewPager.post(runnale)
        }
    }

    override fun getItemCount(): Int {
        return sliderItem.size
    }
    private val runnale= Runnable {
        sliderItem.addAll(sliderItem)
        notifyDataSetChanged()
    }
}