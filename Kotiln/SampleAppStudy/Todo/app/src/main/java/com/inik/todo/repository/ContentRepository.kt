package com.inik.todo.repository

import com.inik.todo.model.ContentEntity
import kotlinx.coroutines.flow.Flow

interface ContentRepository {
    fun loadList() : Flow<List<ContentEntity>>
    suspend fun insert(item: ContentEntity)
}