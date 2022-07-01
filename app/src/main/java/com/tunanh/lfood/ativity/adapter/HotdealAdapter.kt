package com.tunanh.lfood.ativity.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tunanh.lfood.R
import com.tunanh.lfood.ativity.item.itemFood

class HotdealAdapter(var arrayList: ArrayList<itemFood>):
    RecyclerView.Adapter<HotdealAdapter.MyViewHolder>() {
    inner class MyViewHolder (itemView: View):RecyclerView.ViewHolder(itemView){
        var saleOff:ImageView
        var imgfood:ImageView
        var distance:TextView
        var foodName:TextView
        var rating:TextView
    init {
        saleOff=itemView.findViewById(R.id.saleoff)
        imgfood=itemView.findViewById(R.id.item_img_food)
        distance=itemView.findViewById(R.id.item_tv_distance)
        foodName=itemView.findViewById(R.id.item_food_name)
        rating=itemView.findViewById(R.id.rating)


    }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v= LayoutInflater.from(parent.context).inflate(R.layout.item_food,parent,false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currenItem=arrayList[position]
        holder.saleOff.setImageResource(currenItem.saleOff)
        holder.imgfood.setImageResource(currenItem.imgfood)
        holder.distance.text=currenItem.distance
        holder.foodName.text=currenItem.foodName
        holder.rating.text=currenItem.rating
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

}