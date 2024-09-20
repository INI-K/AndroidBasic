package com.inik.shopping.viewholder

import com.inik.shopping.ListAdapter
import com.inik.shopping.databinding.ItemViewpagerBinding
import com.inik.shopping.model.ListItem
import com.inik.shopping.model.ViewPager

class ViewPagerViewHolder(
    binding : ItemViewpagerBinding
): BindingViewHolder<ItemViewpagerBinding>(binding) {
    private val adapter = ListAdapter()

    init {
        binding.viewPager.adapter = adapter
    }

    override fun bind(item: ListItem) {
        super.bind(item)
        item as ViewPager
        adapter.submitList(item.items)
    }
}