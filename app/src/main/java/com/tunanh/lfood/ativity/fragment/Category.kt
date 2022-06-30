package com.tunanh.lfood.ativity.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.GridView
import android.widget.Toast
import com.tunanh.lfood.R
import com.tunanh.lfood.ativity.item.CategoryItem
import com.tunanh.lfood.ativity.adapter.CategoryAdapter
import com.tunanh.lfood.ativity.data.CategoryData

class Category : Fragment() ,AdapterView.OnItemClickListener{
    private var gridView:GridView?=null
    private var array:ArrayList<CategoryItem>?=null
    private var categoryAdapter:CategoryAdapter?=null
    private var categoryData=CategoryData()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_category, container, false)

        gridView=view.findViewById(R.id.grid_category)
        array= ArrayList()
        array=setDataList()
        categoryAdapter= CategoryAdapter(view.context,array!!)
        gridView?.adapter=categoryAdapter
        gridView?.onItemClickListener=this


        return view
    }

    private fun setDataList():ArrayList<CategoryItem>{
        var arrayList:ArrayList<CategoryItem> = ArrayList()
        val imgs= categoryData.img
        val names= categoryData.name
        for (i in 0 until imgs.size-1) {
            arrayList.add(CategoryItem(imgs[i], resources.getString(names[i])))
        }


        return arrayList
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        var categoryItem:CategoryItem=array!!.get(position)
        Toast.makeText(context,categoryItem.name , Toast.LENGTH_SHORT).show()
    }
}
