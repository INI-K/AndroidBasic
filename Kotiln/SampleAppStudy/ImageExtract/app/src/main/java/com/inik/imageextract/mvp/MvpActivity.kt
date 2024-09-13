package com.inik.imageextract.mvp

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.inik.imageextract.databinding.ActivityMvpBinding
import com.inik.imageextract.mvc.ImageCountModel

class MvpActivity : AppCompatActivity(), MvpContractor.View {
    private lateinit var binding: ActivityMvpBinding

    private lateinit var presenter: MvpContractor.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMvpBinding.inflate(layoutInflater).also {
            setContentView(it.root)
            it.view = this
        }

        presenter = MvpPresenter(ImageCountModel(),ImageRepositoryImpl())
        presenter.attachView(this)
    }

    override fun onDestroy() {
        presenter.dettachView()
        super.onDestroy()
    }

    fun loadImage(){
        presenter.loadRandomImage()
    }

    override fun showImage(url: String, color: String) {
        binding.imageView.run {
            setBackgroundColor(Color.parseColor(color))
            load(url){
                crossfade(300)
            }
        }
    }

    override fun showImageCountText(count: Int) {
        binding.imageCountTextView.text = "불러온 이미지 수 : $count"
    }
}