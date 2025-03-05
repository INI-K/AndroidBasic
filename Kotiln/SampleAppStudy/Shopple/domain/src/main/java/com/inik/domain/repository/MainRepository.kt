package com.inik.domain.repository

import com.inik.domain.model.BaseModel
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    fun getModelList() : Flow<List<BaseModel>>
}