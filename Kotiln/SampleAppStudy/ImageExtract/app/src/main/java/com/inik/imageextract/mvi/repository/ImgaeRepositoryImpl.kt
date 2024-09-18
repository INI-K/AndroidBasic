package com.inik.imageextract.mvi.repository

import com.inik.imageextract.RetrofitManager
import com.inik.imageextract.mvi.model.Image
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher


class ImgaeRepositoryImpl(private val dispatcher: CoroutineDispatcher = Dispatchers.IO) :
    ImageRepository {
    override suspend fun getRandomImage() = withContext(dispatcher) {
        RetrofitManager.imageService.getRandomImageSuspend().let {
            Image(it.urls.regular, it.color)
        }
    }
}