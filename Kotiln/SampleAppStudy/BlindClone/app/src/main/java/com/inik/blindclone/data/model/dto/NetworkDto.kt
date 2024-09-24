package com.inik.blindclone.data.model.dto

data class ListResponse(
    val success: Boolean,
    val code: Int,
    val massage: String,
    val data: List<ContentDto>
)
data class ContentResponse(
    val success: Boolean,
    val code: Int,
    val massage: String,
    val data: ContentDto?
)
