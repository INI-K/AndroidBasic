package com.inik.simplechat.chatlist

data class ChatRoomItem (
    val chatRoomId: String,
    val lastMessage: String,
    val otherUserName: String
)