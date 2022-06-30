package com.tunanh.lfood.ativity.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.viewpager2.widget.ViewPager2
import com.tunanh.lfood.R
import com.tunanh.lfood.ativity.adapter.IntroViewPagerAdapter

import me.relex.circleindicator.CircleIndicator3

open class IntroActivity : AppCompatActivity() {
    private var titleList= mutableListOf<String>()
    private var descriptionList= mutableListOf<String>()
    private var imgList= mutableListOf<Int>()
    private var colorList= mutableListOf<Int>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

            postTolist()
            var screen_viewpager=findViewById<ViewPager2>(R.id.screen_viewpager)

            screen_viewpager.adapter=
                IntroViewPagerAdapter(titleList,descriptionList,imgList,colorList)
            screen_viewpager.orientation=ViewPager2.ORIENTATION_HORIZONTAL

            var indecator= findViewById<CircleIndicator3>(R.id.CircleIndicator3)
            indecator.setViewPager(screen_viewpager)

            var intro_button=findViewById<Button>(R.id.intro_button)
            intro_button.setOnClickListener{
                screen_viewpager.apply {
                    beginFakeDrag()
                    fakeDragBy(-10f)
                    endFakeDrag()
                }

            }

        val sharedPreferences: SharedPreferences=getSharedPreferences("MYPREF", Context.MODE_PRIVATE)
        var editor:SharedPreferences.Editor=sharedPreferences.edit()
        var intro_button_skip=findViewById<Button>(R.id.intro_button_skip)
        intro_button_skip.setOnClickListener {
            editor.putBoolean("intro",true)
            editor.apply()
            editor.commit()
            val intent:Intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

    }

    private fun addToList(title:String,description:String,img:Int,color:Int){
        titleList.add(title)
        descriptionList.add(description)
        imgList.add(img)
        colorList.add(color)
    }
    private fun postTolist(){
        addToList("ORDER ONLINE","you can eat anything\nas long as L Food",R.drawable.asset1_4x,R.color.linh)
        addToList("SELECT FOOD","Just sit at home and L food take care",R.drawable.asset2_4x,R.color.linh)
        addToList("DELIVERY","Just sit at home and L food take care",R.drawable.asset3_4x,R.color.linh)
    }
}