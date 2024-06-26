package com.inik.gitlookup.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("login")
    val userName: String,
    @SerializedName("id")
    val id: Int,
)
