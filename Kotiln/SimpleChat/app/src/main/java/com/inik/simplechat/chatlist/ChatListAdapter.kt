package com.inik.simplechat.chatlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.inik.simplechat.databinding.ItemChatroomBinding
import com.inik.simplechat.databinding.ItemUserBinding

class ChatListAdapter(private val onClick: (ChatRoomItem) -> Unit): ListAdapter<ChatRoomItem, ChatListAdapter.ViewHolder>(differ) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemChatroomBinding.inflate(
            LayoutInflater.from(parent.context),
                parent,
                false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class ViewHolder(private val binding: ItemChatroomBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: ChatRoomItem){
            binding.nicknameTextView.text = item.otherUserName
            binding.lastMessgeTextView.text = item.lastMessage

            binding.root.setOnClickListener {
                onClick(item)
            }
        }
    }
    companion object {
        val differ = object: DiffUtil.ItemCallback<ChatRoomItem>(){
            override fun areItemsTheSame(oldItem: ChatRoomItem, newItem: ChatRoomItem): Boolean {
               return oldItem.chatRoomId === newItem.chatRoomId
            }

            override fun areContentsTheSame(oldItem: ChatRoomItem, newItem: ChatRoomItem): Boolean {
                return oldItem == newItem
            }

        }
    }
}