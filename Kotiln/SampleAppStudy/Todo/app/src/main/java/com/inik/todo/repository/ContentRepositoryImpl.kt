package com.inik.todo.repository

import com.inik.todo.data.dao.ContentDao
import com.inik.todo.model.ContentEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ContentRepositoryImpl @Inject constructor(private val contentDao: ContentDao):
    ContentRepository {

    override fun loadList() = contentDao.selectAll()

    override suspend fun insert(item: ContentEntity) {
        contentDao.insert(item)
    }
}