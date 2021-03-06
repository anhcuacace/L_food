package com.tunanh.lfood.ativity.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Base64
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.tunanh.lfood.R
import java.security.MessageDigest





class Loagding : AppCompatActivity() {
    public val time_loading=1000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)
        val sharedPreferences: SharedPreferences =getSharedPreferences("MYPREF", Context.MODE_PRIVATE)
        if(sharedPreferences.getBoolean("intro",false)){
          val handler= Handler(Looper.getMainLooper())
            handler.postDelayed({
                val intent= Intent(this,Login::class.java)
                startActivity(intent)
            }, time_loading.toLong())


        }
        else{
            val handler= Handler(Looper.getMainLooper())
            handler.postDelayed({
                val intent= Intent(this,IntroActivity::class.java)
                startActivity(intent)
            }, time_loading.toLong())
        }

    }

}


