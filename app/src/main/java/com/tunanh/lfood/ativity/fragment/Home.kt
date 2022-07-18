package com.tunanh.lfood.ativity.fragment

import android.database.sqlite.SQLiteDatabase
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.firebase.storage.FirebaseStorage
import com.tunanh.lfood.R

import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase
import com.tunanh.lfood.ativity.adapter.CategoryAdapter
import com.tunanh.lfood.ativity.adapter.HotdealAdapter
import com.tunanh.lfood.ativity.adapter.SliderAdapter
import com.tunanh.lfood.ativity.data.CategoryData
//import com.tunanh.lfood.ativity.data.SQLiteHelper
import com.tunanh.lfood.ativity.item.CategoryItem
import com.tunanh.lfood.ativity.item.SliderItem
import com.tunanh.lfood.ativity.item.itemFood
import me.relex.circleindicator.CircleIndicator3


class Home : Fragment() {

    private var handler = Handler()
//    private var db: SQLiteHelper?=null
    private var viewPager:ViewPager2?=null
    private var categoryData = CategoryData()
    private var itemfood = com.tunanh.lfood.ativity.data.itemfood()
    private lateinit var bitmap: Bitmap
    private var categoryAdapter: CategoryAdapter? = null
    private var option: FirebaseRecyclerOptions<CategoryItem>? = null
//    private var img = categoryData.img
//    private var name = categoryData.name
    val runnable = Runnable {
        if (viewPager!!.currentItem == AddsliderItem().size - 1) {
            viewPager!!.currentItem = 0
        } else {
            viewPager!!.currentItem = viewPager!!.currentItem + 1
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        //database
//        db= SQLiteHelper(view.context,"app.sqlite",null,1)


        //set viewpager
        viewPager = view.findViewById<ViewPager2>(R.id.slide_home)

        var indecator = view.findViewById<CircleIndicator3>(R.id.CircleIndicator3_slide)

        var sliderAdapter = SliderAdapter(AddsliderItem(),)

        viewPager!!.adapter = sliderAdapter

        indecator.setViewPager(viewPager)
        viewPager!!.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnable!!)

                handler.postDelayed(runnable!!, 3000)
            }

        })
//        sliderAdapter.registerAdapterDataObserver(indecator.adapterDataObserver)
        //set recyclerview category

        var recyclerViewCategory = view.findViewById<RecyclerView>(R.id.rcl_category_home)
        recyclerViewCategory.isNestedScrollingEnabled=false
        recyclerViewCategory.setHasFixedSize(false)
        recyclerViewCategory.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        option = FirebaseRecyclerOptions.Builder<CategoryItem>()
            .setQuery(
                FirebaseDatabase.getInstance().getReference().child("Category"),
                CategoryItem::class.java
            ).build()

        categoryAdapter = CategoryAdapter(view.context, option!!, 2)
        recyclerViewCategory?.adapter = categoryAdapter
//        recyclerViewCategory.setHasFixedSize(false)
//        recyclerViewCategory.adapter = CategoryrclvAdapter(setDataCategoryList())
//set recyclerview hot deal
//        var recyclerViewHotdeal = view.findViewById<RecyclerView>(R.id.rcl_hotdealfood_home)
//        recyclerViewHotdeal.isNestedScrollingEnabled=false
//        recyclerViewHotdeal.layoutManager =
//            LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
//        recyclerViewHotdeal.setHasFixedSize(false)
//        recyclerViewHotdeal.adapter = HotdealAdapter(setDataHotDealList(),1)

//      set recyclerview recommend
//        var recyclerViewRecommend = view.findViewById<RecyclerView>(R.id.rcl_recommend_home)
//        recyclerViewRecommend.isNestedScrollingEnabled=false
//        recyclerViewRecommend.layoutManager =
//            LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
//        recyclerViewRecommend.setHasFixedSize(false)
//        recyclerViewRecommend.adapter = HotdealAdapter(setDataHotDealList(),2)



        return view
    }

    private fun AddsliderItem(): ArrayList<SliderItem> {
        var sliderItem: ArrayList<SliderItem> = ArrayList()
        sliderItem.add(SliderItem(R.drawable.slider1))
        sliderItem.add(SliderItem(R.drawable.slider2))
        sliderItem.add(SliderItem(R.drawable.slider3))
        sliderItem.add(SliderItem(R.drawable.slider4))
        return sliderItem

    }
//    private fun setDataHotDealList(): ArrayList<itemFood> {
//        var arrayList: ArrayList<itemFood> = ArrayList()
//        var hotdeal =db!!.GetData("SELECT * FROM food1")
//        while (hotdeal.moveToNext()){
//            arrayList.add(itemFood(byteArrayToBitmap(hotdeal.getBlob(1)),byteArrayToBitmap(hotdeal.getBlob(2)),hotdeal.getString(3),hotdeal.getString(4),hotdeal.getString(5)))
//        }


//        val saleOff = itemfood.saleOff
//        val imgFood = itemfood.imgFood
//        val distance = itemfood.distance
//        val name = itemfood.name
//        val rating = itemfood.ratting
//        for (i in 0 until 10) {
//            if (i % 4 == 0) {
//                arrayList.add(
//                    itemFood(
//                        saleOff[0],
//                        imgFood[0],
//                        resources.getString(distance[0]) +" "+  resources.getString(distance[2]),
//                        resources.getString(name[0]),
//                        resources.getString(rating[0]) +" "+  resources.getString(rating[2])
//                    )
//                )
//            } else if ((i - 1) % 4 == 0) {
//                arrayList.add(
//                    itemFood(
//                        saleOff[1],
//                        imgFood[1],
//                        resources.getString(distance[0]) +" "+  resources.getString(distance[3]),
//                        resources.getString(name[1]),
//                        resources.getString(rating[0]) +" "+  resources.getString(rating[3])
//                    )
//                )
//            } else if ((i - 2) % 4 == 0) {
//                arrayList.add(
//                    itemFood(
//                        saleOff[0],
//                        imgFood[0],
//                        resources.getString(distance[1]) +" "+  resources.getString(distance[2]),
//                        resources.getString(name[0]),
//                        resources.getString(rating[1]) +" "+ resources.getString(rating[2])
//                    )
//                )
//            } else if ((i - 3) % 4 == 0) {
//                arrayList.add(
//                    itemFood(
//                        saleOff[1],
//                        imgFood[1],
//                        resources.getString(distance[1]) +" "+  resources.getString(distance[3]),
//                        resources.getString(name[1]),
//                        resources.getString(rating[1]) +" "+  resources.getString(rating[3])
//                    )
//                )
//            }
//        }


//        return arrayList
//    }
    private fun byteArrayToBitmap(data: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(data, 0, data.size)
    }
    public fun getimgcategory( name:String) : Bitmap {

        var storage= FirebaseStorage.getInstance()
        var imageRef=storage.getReference().child("category").child(name+".png")
        imageRef.getBytes(Long.MAX_VALUE)
            .addOnSuccessListener { byteArray ->
                bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)

            }

        if (bitmap==null){
            bitmap=resources.getDrawable(R.drawable.ic_launcher_foreground,resources.newTheme()).toBitmap()
            return bitmap
        }else{
            return bitmap
        }

    }
//    private fun setDataCategoryList(): ArrayList<CategoryItem> {
//        var arrayList: ArrayList<CategoryItem> = ArrayList()
////        val imgs = categoryData.img
//        val names = categoryData.name
////        for (i in 0 until imgs.size) {
////            arrayList.add(CategoryItem(imgs[i], resources.getString(names[i])))
////        }
//        arrayList.add(CategoryItem(getimgcategory("rice"),resources.getString(names[0])))
//        arrayList.add(CategoryItem(getimgcategory("rice"),resources.getString(names[0])))
//        arrayList.add(CategoryItem(getimgcategory("banh_mi"),resources.getString(names[1])))
//        arrayList.add(CategoryItem(getimgcategory("bubble_tea"),resources.getString(names[3])))
//        arrayList.add(CategoryItem(getimgcategory("fast_food"),resources.getString(names[4])))
//        arrayList.add(CategoryItem(getimgcategory("tea"),resources.getString(names[5])))
//        arrayList.add(CategoryItem(getimgcategory("international"),resources.getString(names[6])))
//        arrayList.add(CategoryItem(getimgcategory("junk_food"),resources.getString(names[7])))
//        arrayList.add(CategoryItem(getimgcategory("vegetable"),resources.getString(names[8])))
//        return arrayList
//    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(runnable,3000)
    }
}





