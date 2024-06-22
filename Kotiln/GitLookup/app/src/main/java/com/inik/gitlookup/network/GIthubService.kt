package com.inik.gitlookup.network

import com.inik.gitlookup.model.Repo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GIthubService {
    @GET("/users/{username}/repos")
    fun listRepos(@Path("username") username: String): Call<List<Repo>>
}