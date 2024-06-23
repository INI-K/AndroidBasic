package com.inik.gitlookup.network

import com.inik.gitlookup.model.Repo
import com.inik.gitlookup.model.UserDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface GIthubService {
    @Headers("Authorization: Bearer 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")
    @GET("/users/{username}/repos")
    fun listRepos(@Path("username") username: String): Call<List<Repo>>
    @Headers("Authorization: Bearer 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")
    @GET("search/users")
    fun searchUsers(@Query("q") query: String): Call<UserDto>

}