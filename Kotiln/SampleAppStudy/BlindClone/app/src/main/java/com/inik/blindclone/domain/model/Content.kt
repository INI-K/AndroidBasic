package com.inik.blindclone.domain.model

import java.io.Serializable
import java.util.Date

data class Content(
    val id: Int? = null,
    val title: String,
    val content: String,
    val category: String,
    val createdDate: Date = Date(),
    val likeCount: Int? = null,
    val commentCount: Int? = null,
    val viewCount: Int? = null
):Serializable