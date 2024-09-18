package com.inik.imageextract.mvi.repository

import com.inik.imageextract.mvi.model.Image

interface ImageRepository {
    suspend fun getRandomImage() : Image
}