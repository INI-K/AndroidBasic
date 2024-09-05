package com.inik.webtoon

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import com.inik.webtoon.databinding.FragmentWebviewBinding

class WebViewFragment(private val position: Int, private val webViewUrl: String): Fragment() {
    private lateinit var binding: FragmentWebviewBinding
    var listener: OnTabLayoutNameChanged? = null
    companion object{
        const val SHARED_PREFERENCES = "WEB_HISTORY"
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWebviewBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.webView.webViewClient = WebtoonWebClient(binding.webViewProgressBar) {url ->
            activity?.getSharedPreferences(SHARED_PREFERENCES,Context.MODE_PRIVATE)?.edit {
                putString("tab$position", url)
                commit()
            }
        }

        binding.webView.settings.javaScriptEnabled = true
        binding.webView.loadUrl(webViewUrl)

        binding.backToLastBtn.setOnClickListener {
            val sharedPreferences = activity?.getSharedPreferences(SHARED_PREFERENCES,Context.MODE_PRIVATE)
            val url = sharedPreferences?.getString("tab$position","")
            if(url.isNullOrEmpty()){
                Toast.makeText(context,"마지막 저장 시점이 없습니다.",Toast.LENGTH_SHORT).show()
            }else{
                binding.webView.loadUrl(url)
            }
        }

        binding.changeTabNameBtn.setOnClickListener {
            val dialog = context?.let { context -> AlertDialog.Builder(context) }
            val editText = EditText(context)

            dialog?.setView(editText)
            dialog?.setPositiveButton("저장"){_,_ ->
                activity?.getSharedPreferences(SHARED_PREFERENCES,Context.MODE_PRIVATE)?.edit {
                    putString("tab${position}_name", editText.text.toString())
                    listener?.nameChanged(position,editText.text.toString())
                }
            }
            dialog?.setNegativeButton("취소"){dialogInterface,_ ->
                dialogInterface.cancel()
            }?.show()
        }
    }

    fun goBack(){
        binding.webView.goBack()
    }

    fun canGoback(): Boolean{
        return binding.webView.canGoBack()
    }
}

interface OnTabLayoutNameChanged{
    fun nameChanged(position: Int, name: String)
}