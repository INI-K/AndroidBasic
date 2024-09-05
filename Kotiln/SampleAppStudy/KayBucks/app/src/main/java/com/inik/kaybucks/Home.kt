package com.inik.kaybucks

data class Home(
    val user: User,
    val appbarImage :String,
    val banner: Banner
)

data class User(
    val nickname: String,
    val startCount: Int,
    val totalCount: Int
)

data class Banner(
    val image :String,
    val contentDescription: String
)
