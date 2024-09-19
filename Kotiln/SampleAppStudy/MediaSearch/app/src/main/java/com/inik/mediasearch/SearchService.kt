package com.inik.mediasearch

import com.inik.mediasearch.model.ImageListresponse
import com.inik.mediasearch.model.VideoListResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface SearchService {
    @Headers("Authorization: KakaoAK 72620bfdc87978fa3b73650400e5c54e")
    @GET("image")
    fun searchImage(@Query("query") query: String): Observable<ImageListresponse>

    @Headers("Authorization: KakaoAK 72620bfdc87978fa3b73650400e5c54e")
    @GET("vclip")
    fun searchVideo(@Query("query") query: String): Observable<VideoListResponse>
}