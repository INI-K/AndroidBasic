package com.inik.imageextract.mvp.repository

interface ImageRepository {

    fun getRandomImage(callback: Callback)

    interface Callback {
        fun loadImage(url: String, color: String)
    }
}