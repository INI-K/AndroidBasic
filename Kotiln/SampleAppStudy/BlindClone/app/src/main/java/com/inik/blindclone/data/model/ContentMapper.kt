package com.inik.blindclone.data.model

import com.inik.blindclone.data.model.dto.ContentDto
import com.inik.blindclone.data.model.entity.ContentEntity
import com.inik.blindclone.domain.model.Content
import java.util.Date

object ContentMapper {
    fun Content.toRequest() = ContentDto(
        id = id,
        title = title,
        content = content,
        category = category,
        likeCount = likeCount,
        commentCount = commentCount,
        viewCount = viewCount
    )

    fun ContentDto.toContent() = Content(
        id = id ?: -1,
        title = title,
        content = content,
        category = category,
        likeCount = likeCount ?: 0,
        commentCount = commentCount ?: 0,
        viewCount = viewCount ?: 0,
        createdDate = createdDate ?: Date(),
    )

    fun Content.toEntity() = ContentEntity(
        id = id ?: -1,
        title = title,
        content = content,
        category = category,
        likeCount = likeCount ?: 0,
        commentCount = commentCount ?: 0,
        viewCount = viewCount ?: 0,
        createDate = createdDate ?: Date()
    )
}