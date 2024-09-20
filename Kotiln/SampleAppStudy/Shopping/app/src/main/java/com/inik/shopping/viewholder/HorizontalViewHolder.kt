package com.inik.shopping.viewholder

import com.inik.shopping.ListAdapter
import com.inik.shopping.databinding.ItemHorizontalBinding
import com.inik.shopping.model.Horizontal
import com.inik.shopping.model.ListItem

class HorizontalViewHolder(
    private val binding : ItemHorizontalBinding
):BindingViewHolder<ItemHorizontalBinding>(binding) {
    private val adapter = ListAdapter()

    init {
        binding.listView.adapter = adapter
    }

    override fun bind(item: ListItem) {
        super.bind(item)
        item as Horizontal
        binding.titleTextView.text = item.title
        adapter.submitList(item.items)
    }
}