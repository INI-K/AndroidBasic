package com.inik.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.inik.data.db.dao.BasketDao
import com.inik.data.db.dao.LikeDao
import com.inik.data.db.dao.PurchaseDao
import com.inik.data.db.entity.BasketProductEntity
import com.inik.data.db.entity.LikeProductEntity
import com.inik.data.db.entity.PurchaseProductEntity

@Database(
    entities = [
        PurchaseProductEntity::class,
        LikeProductEntity::class,
        BasketProductEntity::class
    ],
    version = 1
)
abstract class ApplicationDatabase : RoomDatabase() {
    companion object {
        const val DB_NAME = "ApplicationDatabase.db"
    }

    abstract fun PurchaseDao(): PurchaseDao

    abstract fun LikeDao(): LikeDao

    abstract fun BasketDao(): BasketDao

}