package com.inik.imageextract.mvi

import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import coil.load
import com.inik.imageextract.databinding.ActivityMviBinding
import com.inik.imageextract.mvi.repository.ImgaeRepositoryImpl
import com.inik.imageextract.mvp.ImageRepositoryImpl
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MviActivity : AppCompatActivity() {
    private val viewModel: MviViewModel by viewModels {
        MviViewModel.MviViewModelFactory(ImgaeRepositoryImpl())
    }

    private lateinit var binding: ActivityMviBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMviBinding.inflate(layoutInflater).also {
            setContentView(it.root)
            it.view = this
        }
        observeViewModel()
    }
    fun loadIamge() {
        lifecycleScope.launch {
            viewModel.channel.send(MviIntent.LoadImage)
        }
    }

    private fun observeViewModel(){
        lifecycleScope.launch {
            viewModel.state.collectLatest {state ->
                when(state){
                    is MviState.Idle -> {
                        binding.progrssView.isVisible = false
                    }

                    is MviState.Loading -> {
                        binding.progrssView.isVisible = true
                    }

                    is MviState.LoadedImage -> {
                        binding.progrssView.isVisible = false
                        binding.iamgeView.run {
                            setBackgroundColor(Color.parseColor(state.image.color))
                            load(state.image.color){
                                crossfade(300)
                            }
                        }
                        binding.imageCountTextView.text = "불러온 이미지 수 : ${state.count}"
                    }
                }
            }
        }
    }
}