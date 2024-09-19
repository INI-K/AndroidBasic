package com.inik.mediasearch.list.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.inik.mediasearch.databinding.ItemImageBinding
import com.inik.mediasearch.list.ItemHandler
import com.inik.mediasearch.model.ImageItem
import com.inik.mediasearch.model.ListItem

class ImageViewHolder(
    private val binding: ItemImageBinding,
    private val itemHandler: ItemHandler? = null
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item : ListItem){
        item as ImageItem
        binding.item = item
        binding.handler = itemHandler
    }
}