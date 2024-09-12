package com.inik.imageextract

import retrofit2.Call
import retrofit2.http.GET

import retrofit2.http.Headers


interface ImageService {

    @Headers("Authorization: Client-ID tNY92UY4LbDXceKOiVUeQT-pL5ajgn8eZUNPV2Dy-9g")
    @GET("photos/random")
    fun getRandomImage() : Call<ImageRespnse>
}