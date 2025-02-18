package com.inik.domain.usecase

import com.inik.domain.model.TempModel
import com.inik.domain.repository.TempRepository
import javax.inject.Inject

class TempUseCase @Inject constructor(private val repository: TempRepository) {

    fun getTempModel(): TempModel{
        return repository.getTempModel()
    }
}