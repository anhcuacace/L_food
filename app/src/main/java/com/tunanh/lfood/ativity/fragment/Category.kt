package com.tunanh.lfood.ativity.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.GridView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import com.tunanh.lfood.R
import com.tunanh.lfood.ativity.adapter.CategoryAdapter
import com.tunanh.lfood.ativity.data.CategoryData
import com.tunanh.lfood.ativity.item.CategoryItem

class Category : Fragment(), AdapterView.OnItemClickListener {
    private var gridView: GridView? = null
    private var array: ArrayList<CategoryItem>? = null
    private var categoryAdapter: CategoryAdapter? = null
    private var categoryData = CategoryData()
//    lateinit var storage: FirebaseStorage

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_category, container, false)

        gridView = view.findViewById(R.id.grid_category)
        array = ArrayList()
        array = setDataList()
        categoryAdapter = CategoryAdapter(view.context, array!!,1)
        gridView?.adapter = categoryAdapter
        gridView?.onItemClickListener = this
//        storage = Firebase.storage
//        val storageRef = storage.reference
//        val pathReference = storageRef.child("images/stars.jpg")

        return view
    }

    private fun setDataList(): ArrayList<CategoryItem> {
        var arrayList: ArrayList<CategoryItem> = ArrayList()
//        val imgs = categoryData.img
        val names = categoryData.name
//        for (i in 0 until imgs.size) {
//            arrayList.add(CategoryItem(imgs[i], resources.getString(names[i])))
//        }
arrayList.add(CategoryItem(categoryData.getimgcategory("rice"),resources.getString(names[0])))
        arrayList.add(CategoryItem(categoryData.getimgcategory("rice"),resources.getString(names[0])))
        arrayList.add(CategoryItem(categoryData.getimgcategory("banh_mi"),resources.getString(names[1])))
        arrayList.add(CategoryItem(categoryData.getimgcategory("bubble_tea"),resources.getString(names[3])))
        arrayList.add(CategoryItem(categoryData.getimgcategory("fast_food"),resources.getString(names[4])))
        arrayList.add(CategoryItem(categoryData.getimgcategory("tea"),resources.getString(names[5])))
        arrayList.add(CategoryItem(categoryData.getimgcategory("international"),resources.getString(names[6])))
        arrayList.add(CategoryItem(categoryData.getimgcategory("junk_food"),resources.getString(names[7])))
        arrayList.add(CategoryItem(categoryData.getimgcategory("vegetable"),resources.getString(names[8])))
        return arrayList
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        var categoryItem: CategoryItem = array!!.get(position)
        Toast.makeText(context, categoryItem.name, Toast.LENGTH_SHORT).show()
    }
}


