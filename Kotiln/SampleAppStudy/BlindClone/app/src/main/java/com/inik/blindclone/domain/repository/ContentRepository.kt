package com.inik.blindclone.domain.repository

import com.inik.blindclone.domain.model.Content
import kotlinx.coroutines.flow.Flow

interface ContentRepository {
    fun loadList() : Flow<List<Content>>
    suspend fun save(item: Content):Boolean

    suspend fun update(item: Content):Boolean
}