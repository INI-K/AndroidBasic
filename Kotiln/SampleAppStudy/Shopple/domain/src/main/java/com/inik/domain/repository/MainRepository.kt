package com.inik.domain.repository

import com.inik.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    fun getProductList() : Flow<List<Product>>
}