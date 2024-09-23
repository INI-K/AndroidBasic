package com.inik.todo.repository

import com.inik.todo.model.ContentEntity

interface ContentRepository {
    suspend fun insert(item: ContentEntity)
}