package com.inik.imageextract.mvvm.repository

import com.inik.imageextract.mvvm.model.Image
import io.reactivex.Single

interface ImageRepository {
    fun getRandomImage(): Single<Image>
}