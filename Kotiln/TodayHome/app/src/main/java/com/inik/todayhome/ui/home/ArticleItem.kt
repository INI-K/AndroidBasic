package com.inik.todayhome.ui.home

import android.media.audiofx.AudioEffect.Descriptor

data class ArticleItem(
    var articleId: String,
    var description: String,
    var isBookMark: Boolean,
    var imageUrl: String
)
