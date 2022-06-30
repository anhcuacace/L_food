package com.tunanh.lfood.ativity.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import com.tunanh.lfood.R
import com.tunanh.lfood.ativity.Item.CategoryItem
import com.tunanh.lfood.ativity.adapter.CategoryAdapter

class Category : Fragment() {
    private var gridView:GridView?=null
    private var array:ArrayList<CategoryItem>?=null
    private var categoryAdapter:CategoryAdapter?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_category, container, false)

        gridView=view.findViewById(R.id.grid_category)
        array= ArrayList()
        categoryAdapter= CategoryAdapter(view.context,array)
        gridView?.adapter=categoryAdapter


        return view
    }

    private fun setDataList():ArrayList<CategoryItem>{

    } 
}
