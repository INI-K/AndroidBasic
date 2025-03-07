package com.inik.simplechat

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.database
import com.google.firebase.messaging.messaging
import com.inik.simplechat.Key.Companion.DB_USERS
import com.inik.simplechat.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signUpBtn.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "이메일 또는 패스워드가 입력되지 않았습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Firebase.auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        //회원가입 성공
                        Toast.makeText(this, "회원가입에 성공했습니다. 로그인 해주세요", Toast.LENGTH_SHORT).show()
                    } else {
                        //실패
                        Toast.makeText(this, "회원가입에 실패했습니다.", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        binding.signInBtn.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "이메일 또는 패스워드가 입력되지 않았습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            Firebase.auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { tesk ->
                    val currentUser = Firebase.auth.currentUser
                    if (tesk.isSuccessful && currentUser != null) {
                        //로그인 성공
                        val userId = currentUser.uid

                        Firebase.messaging.token.addOnCompleteListener{
                            val token = it.result
                            val user = mutableMapOf<String,Any>()
                            user["userId"] = userId
                            user["username"] = email
                            user["fcmToken"] = token


                            Firebase.database.reference.child(DB_USERS).child(userId).updateChildren(user)

                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    } else {
                        //로그인 실패
                        Log.e("LoginActivity", "로그인 실패 ! 사유 : ${tesk.exception.toString()}")
                        Toast.makeText(this, "로그인에 실패했습니다.", Toast.LENGTH_SHORT).show()
                    }
                }
        }

    }
}