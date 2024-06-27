package com.inik.simplechat.mypage

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.inik.simplechat.LoginActivity
import com.inik.simplechat.R
import com.inik.simplechat.databinding.FragmentChatlistBinding
import com.inik.simplechat.databinding.FragmentMypageBinding
import com.inik.simplechat.databinding.FragmentUserlistBinding

class MyPageFragment: Fragment(R.layout.fragment_mypage) {
    private lateinit var binding: FragmentMypageBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMypageBinding.bind(view)

        binding.applyBtn.setOnClickListener {
            val username = binding.usernameEditText.text.toString()
            val description = binding.descriptionEditText.text.toString()

            if(username.isEmpty()){
                Toast.makeText(context, "유저이름은 빈값으로 두실 수 없습니다.", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
        }

        binding.signOutBtn.setOnClickListener {
            Firebase.auth.signOut()
            Toast.makeText(context, "로그아웃 했습니다.", Toast.LENGTH_LONG).show()
            startActivity(Intent(context,LoginActivity::class.java))
            activity?.finish()
        }
    }
}