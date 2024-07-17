package com.inik.todayhome.ui.article

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.inik.todayhome.R
import com.inik.todayhome.data.ArticleModel
import com.inik.todayhome.databinding.FragmentArticleBinding

class ArticleFragment:Fragment(R.layout.fragment_article) {
    private lateinit var binding: FragmentArticleBinding
    private val args: ArticleFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentArticleBinding.bind(view)

        val articleId = args.artcleId

        binding.articleToolbar.setupWithNavController(findNavController())

        Firebase.firestore.collection("articles").document(articleId)
            .get()
            .addOnSuccessListener {
                val model = it.toObject<ArticleModel>()
                binding.thumnailTextView.text = model?.description
                binding.thumnailImageView

                Glide.with(binding.thumnailImageView)
                    .load(model?.imageUrl)
                    .into(binding.thumnailImageView)
            }
            .addOnFailureListener {

            }
    }
}