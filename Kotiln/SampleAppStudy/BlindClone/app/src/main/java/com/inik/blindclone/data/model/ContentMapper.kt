package com.inik.blindclone.data.model

import com.inik.blindclone.data.model.dto.ContentDto
import com.inik.blindclone.domain.model.Content

object ContentMapper {
    fun Content.toRequest() = ContentDto(
        id= id,
        title = title,
        content = content,
        category = category,
        likeCount =  likeCount,
        commentCount = commentCount,
        viewCount = viewCount
    )
}