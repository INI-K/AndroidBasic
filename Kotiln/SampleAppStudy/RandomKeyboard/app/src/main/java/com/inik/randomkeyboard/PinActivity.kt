package com.inik.randomkeyboard

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.inik.randomkeyboard.databinding.ActivityPinBinding

class PinActivity: AppCompatActivity() {
    private lateinit var binding: ActivityPinBinding
    private val viewModel: PinViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        binding = ActivityPinBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.viewModel = viewModel
    }
}