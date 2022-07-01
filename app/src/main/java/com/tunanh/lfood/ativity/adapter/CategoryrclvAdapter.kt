package com.tunanh.lfood.ativity.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tunanh.lfood.R
import com.tunanh.lfood.ativity.item.CategoryItem

class CategoryrclvAdapter(var arrayList: ArrayList<CategoryItem>) :
    RecyclerView.Adapter<CategoryrclvAdapter.MyViewHolder>() {


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemimg: ImageView
        var itemText: TextView

        init {
            itemimg = itemView.findViewById(R.id.img_category_rcl)
            itemText = itemView.findViewById(R.id.text_category_rclv)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.item_rclv_category, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currenItem = arrayList[position]
        holder.itemimg.setImageResource(currenItem.icon)
        holder.itemText.text = currenItem.name
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
}