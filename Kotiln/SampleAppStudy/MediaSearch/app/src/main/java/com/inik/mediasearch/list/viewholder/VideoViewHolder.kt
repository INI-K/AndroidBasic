package com.inik.mediasearch.list.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.inik.mediasearch.databinding.ItemImageBinding
import com.inik.mediasearch.databinding.ItemVideoBinding
import com.inik.mediasearch.model.ListItem
import com.inik.mediasearch.model.VideoItem

class VideoViewHolder(
    private val binding: ItemVideoBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item : ListItem){
        item as VideoItem
        binding.item = item
    }
}