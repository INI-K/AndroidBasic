package com.inik.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.inik.data.db.converter.BasketConverter
import com.inik.data.db.converter.PurchaseConverter
import com.inik.domain.model.Category
import com.inik.domain.model.Price
import com.inik.domain.model.Product
import com.inik.domain.model.Shop

@Entity(tableName = "purchase")
@TypeConverters(PurchaseConverter::class)
data class PurchaseProductEntity(
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
fun PurchaseProductEntity.toDomainModel(): Product {
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


