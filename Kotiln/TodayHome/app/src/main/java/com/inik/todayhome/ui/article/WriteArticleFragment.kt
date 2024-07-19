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
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
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
    private lateinit var viewModel: WriteArticleViewModel


    private val pickMedia =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                viewModel.updateSeletedUri(uri)
            } else {
             Toast.makeText(context,"상태 확인 ?",Toast.LENGTH_SHORT).show()
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWriteArticleBinding.bind(view)

        setupViewModel()
        if(viewModel.seletedUri.value == null){
            startPicker()
        }
        setupPhotoImageView()
        setupPhotoDeleteBtn()
        setupSubmitBtn(view)
        setupBackBtn()
        setupAddBtn()


    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(requireActivity()).get<WriteArticleViewModel>()

        viewModel.seletedUri.observe(viewLifecycleOwner) { uri ->
            binding.photoImageView.setImageURI(uri)
            if (uri != null) {
                Log.e("사진 선택","ㅇㅇㅇㅇㅇㅇ")
                binding.photoClearBtn.isVisible = true
                binding.photoAddBtn.isVisible = false
            } else {
                Log.e("사진 선택","ㄴㄴㄴㄴㄴㄴ")
                binding.photoAddBtn.isVisible = true
                binding.photoClearBtn.isVisible = false
            }
        }
    }

    private fun setupAddBtn() {
        binding.photoAddBtn.setOnClickListener {
            startPicker()
        }
    }


    private fun setupPhotoDeleteBtn() {
        binding.photoClearBtn.setOnClickListener {
            viewModel.updateSeletedUri(null)

        }
    }

    private fun setupPhotoImageView() {
        binding.photoImageView.setOnClickListener {
            if (viewModel.seletedUri.value == null) {
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
                    errorHandler(task.exception)
                }
            }
    }
    private fun setupSubmitBtn(view: View) {
        binding.submitBtn.setOnClickListener {
            showProgress()
            if (viewModel.seletedUri.value != null) {
                val photoUri = viewModel.seletedUri.value ?: return@setOnClickListener
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