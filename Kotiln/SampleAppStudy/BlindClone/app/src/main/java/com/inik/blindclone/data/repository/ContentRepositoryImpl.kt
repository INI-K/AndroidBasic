package com.inik.blindclone.data.repository

import com.inik.blindclone.data.model.ContentMapper.toRequest
import com.inik.blindclone.data.source.remote.api.ContentService
import com.inik.blindclone.domain.model.Content
import com.inik.blindclone.domain.repository.ContentRepository
import okio.IOException
import javax.inject.Inject

class ContentRepositoryImpl @Inject constructor(
    private val contentService: ContentService
): ContentRepository{
    override suspend fun save(item: Content): Boolean {
       return try {
           contentService.saveItem(item.toRequest())
           true
       }catch (e : IOException){
           false
       }
    }
}