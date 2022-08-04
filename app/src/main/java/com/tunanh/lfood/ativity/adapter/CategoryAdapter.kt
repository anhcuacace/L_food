package com.tunanh.lfood.ativity.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.tunanh.lfood.R
import com.tunanh.lfood.ativity.item.CategoryItem


class CategoryAdapter(var context: Context, options: FirebaseRecyclerOptions<CategoryItem>, private var type: Int

) :
    FirebaseRecyclerAdapter<CategoryItem,CategoryAdapter.CategoryViewholder>(options) {

        inner class CategoryViewholder(itemView: View):RecyclerView.ViewHolder(itemView){
            var itemimg: ImageView
            var itemText: TextView

        init {

                itemimg = itemView.findViewById(R.id.img_category)
                itemText = itemView.findViewById(R.id.text_category)


        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewholder {
        return if (type==1){
            CategoryViewholder(LayoutInflater.from(parent.context).inflate(R.layout.item_rclv_category,parent,false))
        }else{
            CategoryViewholder(LayoutInflater.from(parent.context).inflate(R.layout.item_rclv_category,parent,false))
        }
    }

    override fun onBindViewHolder(holder: CategoryViewholder, position: Int, model: CategoryItem) {
        holder.itemText.text=model.name

        val storageReference = Firebase.storage.reference.child(sString(model.img.toString()))
        Glide.with(holder.itemView.context)
            .load(storageReference)
            .placeholder(com.firebase.ui.auth.R.drawable.abc_ab_share_pack_mtrl_alpha)
            .error(com.firebase.ui.auth.R.drawable.common_google_signin_btn_icon_dark).into(holder.itemimg)

//        Glide.with(holder.itemimg.context).load(model.img).placeholder(com.firebase.ui.auth.R.drawable.common_google_signin_btn_icon_dark)
//            .error(com.firebase.ui.auth.R.drawable.common_google_signin_btn_icon_dark).into(holder.itemimg)
    }
    private fun sString(string: String):String{
        val so=string.indexOf(".com/",4,true)
        return string.subSequence(so+5,string.length-4).toString()
    }
    }


//    inner class CategoryViewholder(v: ItemCategoryBinding) : RecyclerView.ViewHolder(v.root) {}




//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewholder {
//
//
//        if (type==1){
//            return CategoryViewholder(LayoutInflater.from(parent.context).inflate(R.layout.item_rclv_category,parent,false))
//        }else{
//            return CategoryViewholder(LayoutInflater.from(parent.context).inflate(R.layout.item_rclv_category,parent,false))
//        }
////        return CategoryViewholder(LayoutInflater.from(parent.context).inflate(R.layout.item_rclv_category,parent,false))
//
//    }
//
//    override fun onBindViewHolder(holder: CategoryViewholder, position: Int) {
//        val currenItem = arrayList[position]
////        holder.itemimg.setImageBitmap(currenItem.icon)
//        holder.itemText.text = currenItem.name
//        Glide.with(holder.itemimg.context).load(currenItem.img).placeholder(com.firebase.ui.auth.R.drawable.common_google_signin_btn_icon_dark)
//            .error(com.firebase.ui.auth.R.drawable.common_google_signin_btn_icon_dark).into(holder.itemimg)
//    }
//
//    override fun getItemCount(): Int {
//        return arrayList.size
//    }
//}