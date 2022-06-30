package com.tunanh.lfood.ativity.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.tunanh.lfood.R
import com.tunanh.lfood.ativity.item.CategoryItem

class CategoryAdapter (var context: Context,var arrayList:ArrayList<CategoryItem>): BaseAdapter() {
    override fun getCount(): Int {
        return arrayList.size
    }

    override fun getItem(position: Int): Any {
        return arrayList.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view=View.inflate(context,R.layout.item_category,null)
        var icon = view.findViewById<ImageView>(R.id.img_category)
        var name= view.findViewById<TextView>(R.id.text_category)
        var categoryItem= arrayList.get(position)
        icon.setImageResource(categoryItem.icon)
        name.text=categoryItem.name

        return view
    }
}