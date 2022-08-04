package com.tunanh.lfood.ativity.activity


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.tunanh.lfood.R


class Loagding : AppCompatActivity() {
    private val time_loading:Long = 2000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)
        val sharedPreferences: SharedPreferences =
            getSharedPreferences("MYPREF", Context.MODE_PRIVATE)
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            if (sharedPreferences.getBoolean("intro", false)) {
                if (sharedPreferences.getBoolean("user", false)) {
                    val intent = Intent(this, Login::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    val intent = Intent(this, Login::class.java)
                    startActivity(intent)
                    finish()
                }
            } else {
                val intent = Intent(this, IntroActivity::class.java)
                startActivity(intent)
                finish()
            }
        }, time_loading)
    }
}


