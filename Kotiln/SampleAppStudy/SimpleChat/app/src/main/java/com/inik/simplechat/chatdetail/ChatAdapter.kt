package com.inik.simplechat.chatdetail

import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.inik.simplechat.databinding.ItemChatBinding
import com.inik.simplechat.userlist.UserItem

class ChatAdapter : ListAdapter<ChatItem, ChatAdapter.ViewHolder>(differ) {
    var otherUserItem: UserItem? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemChatBindingViewHolder = ItemChatBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(itemChatBindingViewHolder)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val differ = object : DiffUtil.ItemCallback<ChatItem>() {
            override fun areItemsTheSame(oldItem: ChatItem, newItem: ChatItem): Boolean {
                return oldItem.chatId == newItem.chatId
            }

            override fun areContentsTheSame(oldItem: ChatItem, newItem: ChatItem): Boolean {
                return oldItem == newItem
            }

        }
    }
    inner class ViewHolder(private val binding: ItemChatBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ChatItem) {

            if (item.userId == otherUserItem?.userId) {
                binding.usernameTextView.isVisible = true
                binding.usernameTextView.text = otherUserItem?.username
                binding.messageTextView.text = item.message
                binding.messageTextView.gravity = Gravity.START
                Log.e("확인", "아이템 아이디1 : ${item.userId}")
                Log.e("확인", "아이템 아이디1 : ${otherUserItem?.userId}")
            } else {
                binding.usernameTextView.isVisible = false
                binding.messageTextView.text = item.message
                binding.messageTextView.gravity = Gravity.END
                Log.e("확인", "아이템 아이디2 : ${item.userId}")
                Log.e("확인", "아이템 아이디2 : ${otherUserItem?.userId}")
            }
        }
    }
}