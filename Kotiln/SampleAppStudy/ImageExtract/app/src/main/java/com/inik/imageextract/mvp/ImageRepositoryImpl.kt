package com.inik.imageextract.mvp

import com.inik.imageextract.ImageRespnse
import com.inik.imageextract.RetrofitManager
import com.inik.imageextract.mvp.repository.ImageRepository
import retrofit2.Call
import retrofit2.Response

class ImageRepositoryImpl : ImageRepository {
    override fun getRandomImage(callback: ImageRepository.Callback) {
        RetrofitManager.imageService.getRandomImage()
            .enqueue(object : retrofit2.Callback<ImageRespnse> {
                override fun onResponse(call: Call<ImageRespnse>, response: Response<ImageRespnse>) {
                    if(response.isSuccessful){
                        response.body()?.let {
                            callback.loadImage(it.urls.regular,it.color)
                        }
                    }
                }

                override fun onFailure(p0: Call<ImageRespnse>, p1: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }
}