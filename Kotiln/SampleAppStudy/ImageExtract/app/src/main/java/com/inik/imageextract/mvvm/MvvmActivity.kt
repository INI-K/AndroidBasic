package com.inik.imageextract.mvvm

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.inik.imageextract.databinding.ActivityMvpBinding
import com.inik.imageextract.databinding.ActivityMvvmBinding
import com.inik.imageextract.mvvm.repository.ImageRepositoryImpl

class MvvmActivity: AppCompatActivity() {
    private val viewModel: MvvmViewModel by viewModels {
        MvvmViewModel.MvvmViewModelFactory(ImageRepositoryImpl())
    }

    private lateinit var binding: ActivityMvvmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMvvmBinding.inflate(layoutInflater).also {
            setContentView(it.root)
            it.lifecycleOwner = this
            it.view = this
            it.viewModel = viewModel
        }
    }

    fun loadImage(){
        viewModel.loadRandomImage()
    }
}