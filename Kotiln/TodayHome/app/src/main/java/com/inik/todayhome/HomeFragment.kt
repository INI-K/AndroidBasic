package com.inik.todayhome

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.inik.todayhome.data.ArticleModel

class HomeFragment: Fragment(R.layout.fragment_home) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db = Firebase.firestore

        db.collection("articles").document("UXK79jH9oA3K5i65WrFv")
            .get()
            .addOnSuccessListener {result ->
                val article = result.toObject<ArticleModel>()
                Log.e("아티클 확인", article.toString())
            }
            .addOnFailureListener {
                it.printStackTrace()
            }
    }
}