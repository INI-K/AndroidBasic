package com.inik.shopping

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.inik.shopping.model.ListItem
import com.inik.shopping.viewholder.BindingViewHolder
import com.inik.shopping.viewholder.ViewHolderGenerator

class ListAdapter : ListAdapter<ListItem,BindingViewHolder<*>>(DiffCallBack()) {
    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return item?.viewType?.ordinal ?: -1

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder<*> {
        return ViewHolderGenerator.get(parent,viewType)
    }

    override fun onBindViewHolder(holder: BindingViewHolder<*>, position: Int) {
        val item = getItem(position)
        if(item != null){
            holder.bind(item)
        }
    }
}