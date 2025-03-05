package com.inik.domain.usecase

import com.inik.domain.model.Product
import com.inik.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainUseCase @Inject constructor(private val mainRepository: MainRepository) {

    fun getProductList(): Flow<List<Product>>{
        return mainRepository.getProductList()
    }
}