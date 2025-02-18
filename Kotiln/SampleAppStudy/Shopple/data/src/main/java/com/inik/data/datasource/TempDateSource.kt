package com.inik.data.datasource

import com.inik.domain.model.TempModel
import javax.inject.Inject

class TempDateSource @Inject constructor( ) {
    fun getTempModel(): TempModel{
        return TempModel("testModel222")
    }
}