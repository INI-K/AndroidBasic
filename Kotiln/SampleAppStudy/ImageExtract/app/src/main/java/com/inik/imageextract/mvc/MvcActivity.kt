package com.inik.imageextract.mvc

import android.graphics.Color
import android.media.Image
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.inik.imageextract.databinding.ActivityMvcBinding
import com.inik.imageextract.mvc.provider.ImageProvider

class MvcActivity: AppCompatActivity(),ImageProvider.Callback {
    private lateinit var binding: ActivityMvcBinding

    private val model = ImageCountModel()
    private val imageProvider = ImageProvider(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMvcBinding.inflate(layoutInflater).also {
            setContentView(it.root)
            it.view = this
        }
    }

    fun loadImage(){
        imageProvider.getRandomImage()
    }

    override fun loadImage(url: String, color: String) {
       model.increase()
        with(binding){
            imagView.run {
                setBackgroundColor(Color.parseColor(color))
                load(url)
            }
            imageCountTextView.text = "불러온 이미지 수 : ${model.count}"
        }
    }
}