package com.inik.kotlin_1.part0.chapter2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.inik.kotlin_1.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //SAM
        //Single Abstract Method

        val view  = View(this)
        view.setOnClickListener({println("안녕")})
    }
}