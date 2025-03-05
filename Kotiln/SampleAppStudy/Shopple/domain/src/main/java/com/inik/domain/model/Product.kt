package com.inik.domain.model

data class Product(
    val productId: String,
    val productName: String,
    val imagUrl: String,
    val price: Price,
    val category: Category,
    val shop: Shop,
    val isNew: Boolean,
    val isFreeShipping: Boolean,
    override val type: ModelType = ModelType.PRODUCT
): BaseModel()
