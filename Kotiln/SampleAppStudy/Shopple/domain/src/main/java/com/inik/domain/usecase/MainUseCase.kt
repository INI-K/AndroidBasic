package com.inik.domain.usecase

import com.inik.domain.model.BaseModel
import com.inik.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainUseCase @Inject constructor(private val mainRepository: MainRepository) {

    fun getModelList(): Flow<List<BaseModel>>{
        return mainRepository.getModelList()
    }
}