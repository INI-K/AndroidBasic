package com.inik.imageextract.mvc.provider

import com.inik.imageextract.ImageRespnse
import com.inik.imageextract.RetrofitManager
import retrofit2.Call
import retrofit2.Response

class ImageProvider(private val callback: Callback) {
    fun getRandomImage(){
        RetrofitManager.imageService.getRandomImage()
            .enqueue(object : retrofit2.Callback<ImageRespnse>{
                override fun onResponse(call: Call<ImageRespnse>, respnse: Response<ImageRespnse>) {
                  if(respnse.isSuccessful){
                      respnse.body()?.let {
                          callback.loadImage(it.urls.regular,it.color)
                      }
                  }
                }

                override fun onFailure(p0: Call<ImageRespnse>, p1: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }
    interface Callback{
        fun loadImage(url: String, color: String)
    }
}