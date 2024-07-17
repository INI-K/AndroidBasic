package com.inik.todayhome.ui.article

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.storage
import com.inik.todayhome.R
import com.inik.todayhome.data.ArticleModel
import com.inik.todayhome.databinding.FragmentWriteArticleBinding
import com.inik.todayhome.ui.home.HomeFragmentDirections.Companion.actionHomeFragmentToWriteArticleFragment
import org.xml.sax.ErrorHandler
import java.util.UUID

class WriteArticleFragment : Fragment(R.layout.fragment_write_article) {
    private lateinit var binding: FragmentWriteArticleBinding

    private var selectedUri: Uri? = null

    private val pickMedia =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                selectedUri = uri
                binding.photoImageView.setImageURI(uri)
                binding.photoAddBtn.isVisible = false
                binding.photoClearBtn.isVisible = true
            } else {
             Toast.makeText(context,"상태 확인 ?",Toast.LENGTH_SHORT).show()
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWriteArticleBinding.bind(view)

        startPicker()
        setupPhotoImageView()
        setupPhotoDeleteBtn()
        setupSubmitBtn(view)
        setupBackBtn()
        setupAddBtn()


    }

    private fun setupAddBtn() {
        binding.photoAddBtn.setOnClickListener {
            startPicker()
        }
    }


    private fun setupPhotoDeleteBtn() {
        binding.photoClearBtn.setOnClickListener {
            binding.photoImageView.setImageURI(null)
            selectedUri = null
            binding.photoClearBtn.isVisible = false
            binding.photoAddBtn.isVisible = true
        }
    }

    private fun setupPhotoImageView() {
        binding.photoImageView.setOnClickListener {
            if (selectedUri == null) {
                startPicker()
            }
        }
    }
    private fun setupBackBtn() {
        binding.backBtn.setOnClickListener {
            findNavController().navigate(WriteArticleFragmentDirections.actionBack())
        }
    }
    private fun showProgress(){
        binding.progessBarLayout.isVisible = true

    }
    private fun hideProgress(){
        binding.progessBarLayout.isVisible = false
    }
    private fun uploadImage(
        photoUri: Uri,
        successHandler: (String) -> Unit,
        errorHandler: (Throwable?) -> Unit
    ) {
        val filName = "${UUID.randomUUID()}.png"
        Firebase.storage.reference.child("articles/photo").child(filName)
            .putFile(photoUri)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    //
                    Firebase.storage.reference.child("articles/photo").child(filName)
                        .downloadUrl
                        .addOnSuccessListener { uri ->
                            successHandler(uri.toString())
                        }.addOnFailureListener { exception ->
                            errorHandler(exception)
                        }
                } else {
                    //error
//                    task.exception?.printStackTrace()
                    errorHandler(task.exception)
//                    Log.e("사진", "실패")
//                    Snackbar.make(view,"이미지가 선택되지 않았습니다",Snackbar.LENGTH_SHORT).show()
                }
            }
    }
    private fun setupSubmitBtn(view: View) {
        binding.submitBtn.setOnClickListener {
            showProgress()
            if (selectedUri != null) {
                val photoUri = selectedUri ?: return@setOnClickListener
                uploadImage(
                    photoUri = photoUri,
                    successHandler = {
                        uploadArticle(it, binding.descriptionEditTextView.text.toString())
                    },
                    errorHandler = {
                        Snackbar.make(view, "이미지 업로드에 실패 했습니다.", Snackbar.LENGTH_SHORT).show()
                        hideProgress()
                    })
            } else {
                Snackbar.make(view, "이미지가 선택되지 않았습니다.", Snackbar.LENGTH_SHORT).show()
                hideProgress()
            }
        }
    }
    private fun uploadArticle(photoUri: String, description: String) {
        val articleId = UUID.randomUUID().toString()
        val articleModel = ArticleModel(
            articleId =articleId,
            createAt =  System.currentTimeMillis(),
            description =  description,
            imageUrl = photoUri,
        )

        Firebase.firestore.collection("articles").document(articleId)
            .set(articleModel)
            .addOnSuccessListener {
                findNavController().navigate(WriteArticleFragmentDirections.actionBack())
                hideProgress()
            }.addOnFailureListener {
                it.printStackTrace()
                view?.let{view ->
                    Snackbar.make(view, "글 작성에 실패했습니다..", Snackbar.LENGTH_SHORT).show()
                }
                hideProgress()
            }

    }

    private fun startPicker() {
        pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

}