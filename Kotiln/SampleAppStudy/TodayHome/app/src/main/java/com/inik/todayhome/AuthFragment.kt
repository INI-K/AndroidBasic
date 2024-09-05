package com.inik.todayhome

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.inik.todayhome.databinding.FragmentAuthBinding

class AuthFragment: Fragment(R.layout.fragment_auth) {
    private lateinit var binding: FragmentAuthBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAuthBinding.bind(view)

        setupSignInOutBtn()
        setupSignUpBtn()
    }

    override fun onStart() {
        super.onStart()
        if(Firebase.auth.currentUser == null){
            initViewsToSignOutState()
        }else {
            initViewsToSignInState()
            moveToAction()
        }
    }
    private fun setupSignUpBtn() {
        binding.signUpBtn.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            if(email.isEmpty()|| password.isEmpty()){
                Snackbar.make(binding.root,"이메일 또는 패스워드를 입력해주세요.", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Firebase.auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener {task ->
                    if(task.isSuccessful){
                        Snackbar.make(binding.root,"회원가입에 성공 했습니다.", Snackbar.LENGTH_SHORT).show()
                        initViewsToSignInState()
                    }else{
                        Snackbar.make(binding.root,"회원가입에 실패 했습니다.", Snackbar.LENGTH_SHORT).show()
                    }
                }
                .addOnFailureListener {
                    it.printStackTrace()
                }

        }
    }

    private fun setupSignInOutBtn() {
        binding.signInOutBtn.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            if(Firebase.auth.currentUser == null){
                //로그인
                if(email.isEmpty()|| password.isEmpty()){
                    Snackbar.make(binding.root,"이메일 또는 패스워드를 입력해주세요.", Snackbar.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                Firebase.auth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener {  task->
                        if(task.isSuccessful){
                            moveToAction()

                            initViewsToSignInState()
                        }else{
                            Snackbar.make(binding.root,"이메일 또는 패스워드를 확인해주세요.", Snackbar.LENGTH_SHORT).show()
                        }
                    }

            }else{
                //로그아웃
                Firebase.auth.signOut()
                initViewsToSignOutState()
            }
        }
    }

    private fun initViewsToSignInState(){
        Log.e("확인","1111111111")
        binding.emailEditText.setText(Firebase.auth.currentUser?.email)
        binding.emailEditText.isEnabled = false
        binding.passwordEditText.isVisible = false
        binding.signInOutBtn.text= getString(R.string.logOut)
        binding.signUpBtn.isEnabled = false
    }

    private fun initViewsToSignOutState(){
        Log.e("확인","2222222")
        binding.emailEditText.text.clear()
        binding.passwordEditText.text.clear()
        binding.emailEditText.isEnabled = true
        binding.passwordEditText.isVisible = true
        binding.signInOutBtn.text= getString(R.string.signIn)
        binding.signUpBtn.isEnabled = true
    }
    private fun moveToAction(){
//        val action = AuthFragmentDirections.actionAuthFragmentToHomeFragment()
//        findNavController().navigate(action)
    }
}