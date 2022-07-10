package com.tunanh.lfood.ativity.activity

import android.content.res.Resources
import android.graphics.Bitmap.CompressFormat
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.tunanh.lfood.R
import com.tunanh.lfood.ativity.adapter.FragmentMainAdapter
import com.tunanh.lfood.ativity.data.SQLiteHelper
import java.io.ByteArrayOutputStream
import java.lang.Exception


class MainActivity : AppCompatActivity() {
    private var itemfood = com.tunanh.lfood.ativity.data.itemfood()
//    private val db:SQLiteHelper?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //database
        val db= SQLiteHelper(this.applicationContext,"app.sqlite",null,1)
//setDataHotDealList()
        db!!.QueryData("CREATE TABLE IF NOT EXISTS food1(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,saleoff BLOB,imgfood BLOB,distance VARCHAR,name VARCHAR,ratting VARCHAR)")



        val saleOff = itemfood.saleOff
        val imgFood = itemfood.imgFood
        val distance = itemfood.distance
        val name = itemfood.name
        val rating = itemfood.ratting
        for (i in 0 until 10) {
            if (i % 4 == 0) {
                try {
                    db!!.INSERT_food(

                        getBytesFromBitmap(saleOff[0]),
                        getBytesFromBitmap(imgFood[0]),
                        resources.getString(distance[0]) +" "+  resources.getString(distance[2]),
                        resources.getString(name[0]),
                        resources.getString(rating[0]) +" "+  resources.getString(rating[2])

                    )

                }catch (e:Exception){
                    Toast.makeText(applicationContext, e.toString(), Toast.LENGTH_SHORT).show()
                }
            } else if ((i - 1) % 4 == 0) {
                db!!.INSERT_food(
                    getBytesFromBitmap(saleOff[1]),
                    getBytesFromBitmap(imgFood[1]),
                    resources.getString(distance[0]) +" "+  resources.getString(distance[3]),
                    resources.getString(name[1]),
                    resources.getString(rating[0]) +" "+  resources.getString(rating[3])
                )

            } else if ((i - 2) % 4 == 0) {
                db!!.INSERT_food(
                    getBytesFromBitmap(saleOff[0]),
                    getBytesFromBitmap(imgFood[0]),
                    resources.getString(distance[1]) +" "+  resources.getString(distance[2]),
                    resources.getString(name[0]),
                    resources.getString(rating[1]) +" "+ resources.getString(rating[2])
                )

            } else if ((i - 3) % 4 == 0) {
                db!!.INSERT_food(
                    getBytesFromBitmap(saleOff[1]),
                    getBytesFromBitmap(imgFood[1]),
                    resources.getString(distance[1]) +" "+  resources.getString(distance[3]),
                    resources.getString(name[1]),
                    resources.getString(rating[1]) +" "+  resources.getString(rating[3])
                )

            }
        }
        var tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        var viewPager = findViewById<ViewPager2>(R.id.viewpagermain)
        viewPager.isUserInputEnabled=false
        viewPager.adapter = FragmentMainAdapter(this)

        TabLayoutMediator(tabLayout, viewPager) { tab, index ->
            tab.text = when (index) {
                0 -> {
                    "home"
                }
                1 -> {
                    "category"
                }
                2 -> {
                    "wish list"
                }
                3 -> {
                    "more"
                }
                else -> {
                    throw Resources.NotFoundException("position not found")
                }
            }
        }.attach()


    }
    private fun setDataHotDealList() {




    }
    fun getBytesFromBitmap(drawable: Int): ByteArray {
        var d=resources.getDrawable(drawable)
        val bitmap = (d as BitmapDrawable).bitmap
        val stream = ByteArrayOutputStream()
        bitmap.compress(CompressFormat.JPEG, 100, stream)
        return stream.toByteArray()
    }
}