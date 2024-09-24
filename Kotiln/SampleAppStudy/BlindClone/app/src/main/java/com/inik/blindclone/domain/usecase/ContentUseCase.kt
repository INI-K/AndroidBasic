package com.inik.blindclone.domain.usecase

import com.inik.blindclone.domain.model.Content
import com.inik.blindclone.domain.repository.ContentRepository
import javax.inject.Inject

class ContentUseCase @Inject constructor(
    private val contentRepository: ContentRepository
) {
    fun loadList() = contentRepository.loadList()
    suspend fun save(item: Content) = contentRepository.save(item)
}