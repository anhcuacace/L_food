package com.tunanh.lfood.ativity.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.tunanh.lfood.R
import com.tunanh.lfood.ativity.adapter.IntroViewPagerAdapter

import me.relex.circleindicator.CircleIndicator3

open class IntroActivity : AppCompatActivity() {
    private var titleList= mutableListOf<String>()
    private var descriptionList= mutableListOf<String>()
    private var imgList= mutableListOf<Int>()
    private var colorList= mutableListOf<Int>()
    private var intro_button: Button?=null
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor:SharedPreferences.Editor


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

            postTolist()
            val screen_viewpager=findViewById<ViewPager2>(R.id.screen_viewpager)

            screen_viewpager.adapter=
                IntroViewPagerAdapter(titleList,descriptionList,imgList)
            screen_viewpager.orientation=ViewPager2.ORIENTATION_HORIZONTAL

            val indecator= findViewById<CircleIndicator3>(R.id.CircleIndicator3)
            indecator.setViewPager(screen_viewpager)

            intro_button=findViewById(R.id.intro_button)
//            intro_button!!.setOnClickListener{
//                screen_viewpager.apply {
//                    beginFakeDrag()
//                    fakeDragBy(-10f)
//                    endFakeDrag()
//                }
//            }
        sharedPreferences=getSharedPreferences("MYPREF", Context.MODE_PRIVATE)
        editor=sharedPreferences.edit()
        screen_viewpager.registerOnPageChangeCallback(object :ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (screen_viewpager.currentItem==2){
                    changbutton()
                }
                else{
                    intro_button?.setText("Next")
                    intro_button!!.setOnClickListener{
                        screen_viewpager.apply {
                            beginFakeDrag()
                            fakeDragBy(-200f)
                            endFakeDrag()
                        }
                    }
                }
            }
        })


        val intro_button_skip=findViewById<Button>(R.id.intro_button_skip)
        intro_button_skip.setOnClickListener {
            editor.putBoolean("intro",true)
            editor.apply()
            editor.commit()
            val intent=Intent(this,Login::class.java)
            startActivity(intent)
        }

    }
    private fun changbutton(){
        intro_button?.setText(resources.getText(R.string.Getstarted))
        intro_button?.setOnClickListener {
            editor.putBoolean("intro",true)
            editor.apply()
            editor.commit()
            val intent=Intent(this,Login::class.java)
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
    private var count = 0
    override fun onBackPressed() {
        count++
        if (count > 1) {
            finishAffinity()
        } else {
            Toast.makeText(this, "Press back again to Leave!", Toast.LENGTH_SHORT).show()

            // resetting the counter in 2s
            val handler = Handler(Looper.getMainLooper())

            handler.postDelayed({ count = 0 }, 2000)
        }
        super.onBackPressed()
    }
}