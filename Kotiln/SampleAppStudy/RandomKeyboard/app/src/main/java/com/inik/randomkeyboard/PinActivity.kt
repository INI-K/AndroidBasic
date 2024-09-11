package com.inik.randomkeyboard

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.inik.randomkeyboard.databinding.ActivityPinBinding
import com.inik.randomkeyboard.widget.ShuffleNumberKeyBoard

class PinActivity: AppCompatActivity(), ShuffleNumberKeyBoard.KeyPadListener{
    private lateinit var binding: ActivityPinBinding
    private val viewModel: PinViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPinBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.e("핀액티비티","열림?")
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.shuffleKeyBoard.setKeyPadListener(this)

        viewModel.messageLiveData.observe(this){
            Toast.makeText(this,it,Toast.LENGTH_SHORT).show()
        }
    }
    override fun onClickNum(num: String) {
        viewModel.input(num)
    }

    override fun onClickDelete() {
        viewModel.delete()
    }

    override fun conClickDone() {
        viewModel.done()
    }
}