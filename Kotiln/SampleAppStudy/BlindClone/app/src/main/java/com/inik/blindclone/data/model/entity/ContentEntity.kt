package com.inik.blindclone.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.Date

@Entity("Content")
data class ContentEntity(
    @PrimaryKey(false)
    val id: Int,

    @ColumnInfo
    var title: String,

    @ColumnInfo
    var content: String,

    @ColumnInfo
    var category: String,

    @ColumnInfo
    var createDate: Date,

    @ColumnInfo
    var likeCount: Int,

    @ColumnInfo
    var commentCount: Int,

    @ColumnInfo
    var viewCount: Int
) : Serializable
