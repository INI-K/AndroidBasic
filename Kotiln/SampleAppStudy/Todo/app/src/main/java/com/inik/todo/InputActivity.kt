package com.inik.todo

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.inik.todo.databinding.ActivityInputBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InputActivity: AppCompatActivity() {
    private lateinit var binding: ActivityInputBinding
    private val viewModel: InputViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputBinding.inflate(layoutInflater).apply {
            setContentView(root)
            lifecycleOwner = this@InputActivity
            viewModel = this@InputActivity.viewModel
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel.doneEvent.observe(this){
            Toast.makeText(this,"완료!",Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true

    }
}