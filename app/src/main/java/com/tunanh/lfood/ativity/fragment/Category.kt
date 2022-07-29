package com.tunanh.lfood.ativity.fragment

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.GridLayout
import android.widget.Toast
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.tunanh.lfood.R
import com.tunanh.lfood.ativity.adapter.CategoryAdapter
import com.tunanh.lfood.ativity.data.CategoryData
import com.tunanh.lfood.ativity.item.CategoryItem
import java.text.ParsePosition

class Category : Fragment(), AdapterView.OnItemClickListener {
    private var recyclerView: RecyclerView? = null

    private var categoryAdapter: CategoryAdapter? = null
    private var categoryData = CategoryData()
    private var option: FirebaseRecyclerOptions<CategoryItem>? = null

    //    private lateinit var bitmap: Bitmap
//    lateinit var storage: FirebaseStorage
    private lateinit var dbRef: DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_category, container, false)

//dbRef= FirebaseDatabase.getInstance().getReference("Category")

//        recyclerView?.onItemClickListener = this
//        storage = Firebase.storage
//        val storageRef = storage.reference
//        val pathReference = storageRef.child("images/stars.jpg")

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.grid_category)
//        array = arrayListOf<CategoryItem>()
//        array = ArrayList()
//        array = setDataList()


        recyclerView?.layoutManager = GridLayoutManager(context, 2)
//        getCategoryData()

        option = FirebaseRecyclerOptions.Builder<CategoryItem>()
            .setQuery(
                FirebaseDatabase.getInstance().getReference().child("Category"),
                CategoryItem::class.java
            ).build()

        categoryAdapter = CategoryAdapter(view.context, option!!, 1)
        recyclerView?.adapter = categoryAdapter
    }

//    class CategoryViewholder(v: View) : RecyclerView.ViewHolder(v) {
//
//    }


//    private fun getCategoryData() {
//
//        var firebaseRecyclerAdapter= object :
//            FirebaseRecyclerAdapter<CategoryItem, CategoryViewholder>(
//                CategoryItem::class.java,
//                R.layout.item_category,
//                CategoryViewholder::class.java,
//                dbRef
//            )
//        {
//                override fun populateViewHolder(viewholder: CategoryViewholder,model:CategoryItem,position: Int){
//
//                }
//            }
//
//    }
//    public fun getimgcategory( name:String) : Bitmap {

//        var storage= FirebaseStorage.getInstance()
//        var imageRef=storage.getReference().child("category").child(name+".png")
//        imageRef.getBytes(Long.MAX_VALUE)
//            .addOnSuccessListener { byteArray ->
//                bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
//
//            }
//
//        if (bitmap==null){
//            bitmap=resources.getDrawable(R.drawable.ic_launcher_foreground,resources.newTheme()).toBitmap()
//            return bitmap
//        }else{
//            return bitmap
//        }

    //    }
//    private fun setDataList(): ArrayList<CategoryItem> {
//
//        var arrayList: ArrayList<CategoryItem> = ArrayList()
////        val imgs = categoryData.img
//        val names = categoryData.name
////        for (i in 0 until imgs.size) {
////            arrayList.add(CategoryItem(imgs[i], resources.getString(names[i])))
////        }
//        arrayList.add(CategoryItem(getimgcategory("rice"), resources.getString(names[0])))
//        arrayList.add(CategoryItem(getimgcategory("rice"), resources.getString(names[0])))
//        arrayList.add(CategoryItem(getimgcategory("banh_mi"), resources.getString(names[1])))
//        arrayList.add(CategoryItem(getimgcategory("bubble_tea"), resources.getString(names[3])))
//        arrayList.add(CategoryItem(getimgcategory("fast_food"), resources.getString(names[4])))
//        arrayList.add(CategoryItem(getimgcategory("tea"), resources.getString(names[5])))
//        arrayList.add(CategoryItem(getimgcategory("international"), resources.getString(names[6])))
//        arrayList.add(CategoryItem(getimgcategory("junk_food"), resources.getString(names[7])))
//        arrayList.add(CategoryItem(getimgcategory("vegetable"), resources.getString(names[8])))
//        return arrayList
//    }


    override fun onStart() {
        super.onStart()
        categoryAdapter?.startListening()
    }

    override fun onStop() {
        super.onStop()
//        categoryAdapter?.stopListening()
    }

//    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//        TODO("Not yet implemented")
//    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

//        var categoryItem: CategoryItem = option!!.get(position)
//        Toast.makeText(context, categoryItem.name, Toast.LENGTH_SHORT).show()
    }
}

//    class CategoryViewholder (itemView: View?):RecyclerView.ViewHolder(itemView!!){
//
//    }


