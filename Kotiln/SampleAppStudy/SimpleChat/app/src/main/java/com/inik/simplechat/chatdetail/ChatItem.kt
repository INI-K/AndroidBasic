package com.inik.simplechat.chatdetail

data class ChatItem(
    var chatId: String ?= null,
    val userId: String?= null,
    val message: String?= null,
)
