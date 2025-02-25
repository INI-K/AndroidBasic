package com.inik.chapter2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val numberTextView = findViewById<TextView>(R.id.numberTextView)
        val resetBtn = findViewById<Button>(R.id.resetBtn)
        val plusBtn = findViewById<Button>(R.id.plusBtn)

        var number = 0


        resetBtn.setOnClickListener {
            number =0
            numberTextView.text = number.toString()
            Log.d("OnClick","리셋된 숫자 $number")
        }
        plusBtn.setOnClickListener{
            number += 1
            numberTextView.text = number.toString()
            Log.d("Onclick", "플러스된 숫자 $number")
        }
    }
}