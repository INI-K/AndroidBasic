package com.inik.todayhome.ui.bookmark

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.inik.todayhome.R
import com.inik.todayhome.data.ArticleModel
import com.inik.todayhome.databinding.FragmentBookMarkArticleBinding
import com.inik.todayhome.ui.home.HomeArticleAdapter


class BookMarkArticleFragment : Fragment(R.layout.fragment_book_mark_article) {
    private lateinit var binding: FragmentBookMarkArticleBinding
    private lateinit var bookmarkAdapter: BookMarkArticleAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentBookMarkArticleBinding.bind(view)

        binding.toolbar.setupWithNavController(findNavController())

        bookmarkAdapter = BookMarkArticleAdapter {
            findNavController().navigate(
                BookMarkArticleFragmentDirections.actionBookMarkArticleFragmentToArticleFragment(
                    it.articleId.orEmpty()
                )
            )
        }

        binding.articleRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = bookmarkAdapter
        }

        val uid = Firebase.auth.currentUser?.uid.orEmpty()
        Firebase.firestore.collection("bookmark")
            .document(uid)
            .get()
            .addOnSuccessListener {
                val list = it.get("articleIds") as List<*>

                if (list.isNotEmpty()) {
                    Firebase.firestore.collection("articles")
                        .whereIn("articleId", list)
                        .get()
                        .addOnSuccessListener { result ->
                            bookmarkAdapter.submitList(result.map { article -> article.toObject() })
                        }.addOnFailureListener { e ->
                            e.printStackTrace()
                        }
                }
            }.addOnFailureListener {
                it.printStackTrace()
                //스낵바 or 토스트
            }
    }

}