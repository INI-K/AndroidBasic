package com.inik.blindclone.domain.repository

import com.inik.blindclone.domain.model.Content

interface ContentRepository {
    suspend fun save(item: Content):Boolean

}