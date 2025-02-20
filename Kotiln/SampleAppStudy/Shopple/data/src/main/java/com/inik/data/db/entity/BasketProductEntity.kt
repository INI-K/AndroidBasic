package com.inik.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.inik.data.db.converter.BasketConverter
import com.inik.domain.model.Category
import com.inik.domain.model.Price
import com.inik.domain.model.Product
import com.inik.domain.model.Shop

@Entity(tableName = "basket")
@TypeConverters(BasketConverter::class)
data class BasketProductEntity(
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

fun BasketProductEntity.toDomainModel(): Product{
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
