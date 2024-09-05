package com.inik.webtoon

import android.graphics.Bitmap
import android.view.View
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar

class WebtoonWebClient(
    private val progressBar: ProgressBar,
    private val saveDate:(String) -> Unit,
    ): WebViewClient() {
    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        //https://comic.naver.com/webtoon/detail?titleId=808198&no=64&week=thu
        if (request != null && request.url.toString().contains("comic.naver.com/webtoon/detail")){
            saveDate(request.url.toString())
        }

        return super.shouldOverrideUrlLoading(view, request)
    }
    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)
        progressBar.visibility = View.VISIBLE
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)

        progressBar.visibility = View.GONE
    }

}