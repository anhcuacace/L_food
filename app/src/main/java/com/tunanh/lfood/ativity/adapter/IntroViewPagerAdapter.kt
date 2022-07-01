package com.tunanh.lfood.ativity.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tunanh.lfood.R

class IntroViewPagerAdapter(
    private var title: List<String>,
    private var description: List<String>,
    private var img: List<Int>,
    private var color: List<Int>
) : RecyclerView.Adapter<IntroViewPagerAdapter.Pager2ViewHolder>() {

    inner class Pager2ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemIntroTitle: TextView = itemView.findViewById(R.id.Intro_title)
        var itemIntrodescription: TextView = itemView.findViewById(R.id.intro_description)
        var itemIntroimg: ImageView = itemView.findViewById(R.id.intro_img)

        init {
            itemIntroimg.setOnClickListener { v: View ->
                val position = adapterPosition

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Pager2ViewHolder {
        return Pager2ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_screen, parent, false)
        )
    }

    override fun onBindViewHolder(holder: IntroViewPagerAdapter.Pager2ViewHolder, position: Int) {
        holder.itemIntroTitle.text = title[position]
        holder.itemIntrodescription.text = description[position]
        holder.itemIntroimg.setImageResource(img[position])
        holder.itemView.setBackgroundColor(color[position])

    }

    override fun getItemCount(): Int {
        return title.size
    }


}


