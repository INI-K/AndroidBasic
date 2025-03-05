package com.inik.domain.repository

import com.inik.domain.model.Product

interface MainRepository {
    fun getProductList() : List<Product>
}