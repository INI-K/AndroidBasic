package com.inik.imageextract

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.inik.imageextract.databinding.ActivityMainBinding
import com.inik.imageextract.mvc.MvcActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
            it.view = this
        }
    }

    fun openMvc(){
        startActivity(Intent(this,MvcActivity::class.java))
    }
    fun openMvp(){

    }
    fun openMvvm(){

    }
    fun openMvi(){

    }
}