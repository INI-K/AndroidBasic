package com.inik.data.repository

import com.inik.data.datasource.TempDateSource
import com.inik.domain.model.TempModel
import com.inik.domain.repository.TempRepository
import javax.inject.Inject

class TempRepositoryImpl @Inject constructor(private val dataSource : TempDateSource) : TempRepository {
    override fun getTempModel(): TempModel {
        return dataSource.getTempModel()
    }
}