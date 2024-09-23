package com.inik.todo

import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView
import com.inik.todo.databinding.ItemContentBinding
import com.inik.todo.model.ContentEntity

class ContentViewHolder(
    private val binding: ItemContentBinding,
    private val handler: MainActivity.Handler? =null
): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: ContentEntity){
        binding.item = item
        binding.handler = handler

        binding.contentCheckBox.paintFlags  =   if(item.isDone){
            Paint.STRIKE_THRU_TEXT_FLAG
        }else{
            0
        }
    }
}