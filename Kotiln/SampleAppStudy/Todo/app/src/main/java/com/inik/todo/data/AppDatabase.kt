package com.inik.todo.data.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.inik.todo.model.ContentEntity

@Database(entities = [ContentEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun contentDao(): ContentDao
}