package com.tunanh.lfood.ativity.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.tunanh.lfood.R
import com.tunanh.lfood.ativity.item.CategoryItem
import com.tunanh.lfood.databinding.ItemCategoryBinding
import com.tunanh.lfood.databinding.ItemRclvCategoryBinding

class CategoryAdapter(var context: Context, var arrayList: ArrayList<CategoryItem>,var type:Int) :RecyclerView.Adapter<CategoryAdapter.CategoryViewholder>() {
    inner class CategoryViewholder(itemView: View):RecyclerView.ViewHolder(itemView){
        var itemimg: ImageView
        var itemText: TextView

        init {
            if (type==1){
                itemimg = itemView.findViewById(R.id.img_category)
                itemText = itemView.findViewById(R.id.text_category)
            }else{
                itemimg = itemView.findViewById(R.id.img_category_rcl)
                itemText = itemView.findViewById(R.id.text_category_rclv)
            }

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewholder {

        if (type==1){
        return CategoryViewholder(LayoutInflater.from(parent.context).inflate(R.layout.item_rclv_category,parent,false))
        }else{
            return CategoryViewholder(LayoutInflater.from(parent.context).inflate(R.layout.item_rclv_category,parent,false))
        }
    }

    override fun onBindViewHolder(holder: CategoryViewholder, position: Int) {
        val currenItem = arrayList[position]
        holder.itemimg.setImageBitmap(currenItem.icon)
        holder.itemText.text = currenItem.name
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
}