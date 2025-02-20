package com.inik.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.inik.data.db.converter.BasketConverter
import com.inik.data.db.converter.LikeConverter
import com.inik.domain.model.Category
import com.inik.domain.model.Price
import com.inik.domain.model.Product
import com.inik.domain.model.Shop

@Entity(tableName = "like")
@TypeConverters(LikeConverter::class)
data class LikeProductEntity(
    @PrimaryKey
    val productId: String,
    val productName: String,
    val imagUrl: String,
    val price: Price,
    val category: Category,
    val shop: Shop,
    val isNew: Boolean,
    val isFreeShipping: Boolean
)
fun LikeProductEntity.toDomainModel(): Product {
    return Product(
        productId = productId,
        productName = productName,
        imagUrl = imagUrl,
        price = price,
        category = category,
        shop = shop,
        isNew = isNew,
        isFreeShipping = isFreeShipping
    )
}
