package com.inik.mediasearch.list.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.inik.mediasearch.databinding.ItemImageBinding
import com.inik.mediasearch.model.ImageItem
import com.inik.mediasearch.model.ListItem

class ImageViewHolder(
    private val binding: ItemImageBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item : ListItem){
        item as ImageItem
        binding.item = item
    }
}