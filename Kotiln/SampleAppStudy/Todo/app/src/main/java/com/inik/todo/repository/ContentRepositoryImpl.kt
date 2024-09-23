package com.inik.todo.repository

import com.inik.todo.data.dao.ContentDao
import com.inik.todo.model.ContentEntity
import javax.inject.Inject

class ContentRepositoryImpl @Inject constructor(private val contentDao: ContentDao): ContentRepository {
    override suspend fun insert(item: ContentEntity) {
        contentDao.insert(item)
    }
}