package com.inik.todayhome.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.inik.todayhome.R
import com.inik.todayhome.data.ArticleModel
import com.inik.todayhome.databinding.FragmentHomeBinding

class HomeFragment: Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        val articleAdapter = HomeArticleAdapter{
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToArticleFragment(artcleId = it.articleId.orEmpty()))
        }
        binding.homeRecyclerView.apply {
            layoutManager = GridLayoutManager(context,2)
                adapter = articleAdapter
        }

        Firebase.firestore.collection("articles")
            .get()
            .addOnSuccessListener {result ->
                val list = result.map {
                    it.toObject<ArticleModel>()
                }
                articleAdapter.submitList(list)
            }


//        val db = Firebase.firestore
//
//        db.collection("articles").document("UXK79jH9oA3K5i65WrFv")
//            .get()
//            .addOnSuccessListener {result ->
//                val article = result.toObject<ArticleModel>()
//                Log.e("아티클 확인", article.toString())
//            }
//            .addOnFailureListener {
//                it.printStackTrace()
//            }

        setupWriteBtn(view)


    }

    private fun setupWriteBtn(view: View) {
        binding.writeBtn.setOnClickListener{
            if(Firebase.auth.currentUser !=null){
                val action = HomeFragmentDirections.actionHomeFragmentToWriteArticleFragment()
                findNavController().navigate(action)
            }else{
                Snackbar.make(view,"로그인 후 사용해주세요",Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}