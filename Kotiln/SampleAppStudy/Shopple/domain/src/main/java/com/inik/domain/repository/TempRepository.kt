package com.inik.domain.repository

import com.inik.domain.model.TempModel

interface TempRepository {
    fun getTempModel(): TempModel
}