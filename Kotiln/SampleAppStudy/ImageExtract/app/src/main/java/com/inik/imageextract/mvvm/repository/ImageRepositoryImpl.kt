package com.inik.imageextract.mvvm.repository

import com.inik.imageextract.RetrofitManager
import com.inik.imageextract.mvvm.model.Image
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ImageRepositoryImpl: ImageRepository {
    override fun getRandomImage() = RetrofitManager.imageService.getRandomImageRx()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .flatMap { item ->
            Single.just(Image(item.urls.regular,item.color))
        }
}