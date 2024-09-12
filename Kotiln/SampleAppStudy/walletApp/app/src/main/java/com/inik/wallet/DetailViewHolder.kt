package com.inik.wallet

import androidx.recyclerview.widget.RecyclerView
import com.inik.wallet.databinding.ItemDetailBinding
import com.inik.wallet.model.DetailItem

class DetailViewHolder(private val binding: ItemDetailBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: DetailItem){
        binding.item = item
    }
}