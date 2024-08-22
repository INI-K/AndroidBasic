package com.inik.keypad

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.inik.keypad.databinding.ActivityMainBinding


/*금융서비스
//보안 키패드구성
//머터리얼 디자인 적용
//휴대폰 인증 One Task UI 구성
//Timer를 활용한 인증번호 입력시간 제어
//문자 자동 인식 기능

///
//GridLayOut
//DataBinding
//BindingAdapter
//정규표현식
//TextInputLayout
//CountDownTiemr
//Sms Retriever*/

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.view = this
    }

    fun openShuffle(){

    }

    fun openVerfiOtp(){

    }
}