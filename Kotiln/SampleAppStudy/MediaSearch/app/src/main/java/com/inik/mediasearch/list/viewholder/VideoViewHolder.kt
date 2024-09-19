package com.inik.mediasearch.list.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.inik.mediasearch.databinding.ItemImageBinding
import com.inik.mediasearch.databinding.ItemVideoBinding
import com.inik.mediasearch.list.ItemHandler
import com.inik.mediasearch.model.ListItem
import com.inik.mediasearch.model.VideoItem
import java.security.PrivateKey

class VideoViewHolder(
    private val binding: ItemVideoBinding,
    private val itemHandler: ItemHandler? = null
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item : ListItem){
        item as VideoItem
        binding.item = item
        binding.handler = itemHandler
    }
}