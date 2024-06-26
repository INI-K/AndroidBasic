package com.inik.simplechat

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.inik.simplechat.databinding.ActivityMainBinding
import com.inik.simplechat.userlist.UserFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val userFragment= UserFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val currentUser = Firebase.auth.currentUser

        if (currentUser == null) {
            //로그인 안되어 있음
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        binding.bottomNavigationView.setOnItemSelectedListener {menuItem ->
            when(menuItem.itemId){
                R.id.userList -> {
                    replaceFragment(userFragment)
                    return@setOnItemSelectedListener true
                }

                R.id.chatRoomList -> {
                    return@setOnItemSelectedListener true
                }
                R.id.myPage -> {
                    return@setOnItemSelectedListener true
                }else -> {
                    false
                }
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .apply {
                replace(R.id.frameLayout, fragment)
                commit()
            }

    }
}