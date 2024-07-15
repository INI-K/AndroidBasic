package com.inik.zenli

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.inik.zenli.databinding.ActivityEmailLoginBinding

class EmailLoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEmailLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmailLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.donBtn.setOnClickListener {
            if(binding.emailEditText.text.isNotEmpty()){
                //입력함
                val data = Intent().apply {
                    putExtra("email",binding.emailEditText.text.toString())
                }
                setResult(RESULT_OK, data)
                finish()
            }else{
                Toast.makeText(this,"이메일을 입력해주세요",Toast.LENGTH_SHORT).show()
            }
        }

    }
}