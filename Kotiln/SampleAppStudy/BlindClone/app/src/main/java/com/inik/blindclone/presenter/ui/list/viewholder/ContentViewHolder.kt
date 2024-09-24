package com.inik.blindclone.presenter.ui.list.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.inik.blindclone.databinding.ItemContentBinding
import com.inik.blindclone.domain.model.Content
import com.inik.blindclone.presenter.ui.MainActivity

class ContentViewHolder(
    private val binding : ItemContentBinding,
    private val handler: MainActivity.Handler
): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Content){
        binding.item = item
        binding.handler = handler
    }
}