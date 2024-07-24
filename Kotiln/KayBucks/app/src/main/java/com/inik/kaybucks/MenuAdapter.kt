package com.inik.kaybucks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.inik.kaybucks.databinding.ItemMenuBinding
import com.inik.kaybucks.databinding.ItemMenuRowBinding

class MenuAdapter: ListAdapter<MenuItem, MenuAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder(private val binding: ItemMenuRowBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: MenuItem){
            binding.titleTextView.text = item.name
            Glide.with(binding.imageView)
                .load(item.image)
                .circleCrop()
                .into(binding.imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
         ItemMenuRowBinding.inflate(
             LayoutInflater.from(parent.context),
             parent,
             false
         )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
    companion object{
        val diffUtil = object : DiffUtil.ItemCallback<MenuItem>(){
            override fun areItemsTheSame(oldItem: MenuItem, newItem: MenuItem): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: MenuItem, newItem: MenuItem): Boolean {
                return oldItem == newItem
            }

        }
    }

}