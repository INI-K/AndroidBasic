package com.inik.todayhome.ui.bookmark

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.inik.todayhome.data.ArticleModel
import com.inik.todayhome.databinding.ItemArtcleBinding

class BookMarkArticleAdapter(val onItemClicked:(ArticleModel) -> Unit):ListAdapter<ArticleModel,BookMarkArticleAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder(private val binding: ItemArtcleBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(articleModel: ArticleModel){
            binding.thumnailTextView.text = articleModel.description

            binding.bookmarkImageBtn.isVisible = false

            Glide.with(binding.thumnailImageView)
                .load(articleModel.imageUrl)
                .into(binding.thumnailImageView)


            binding.root.setOnClickListener {
                onItemClicked(articleModel)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemArtcleBinding.inflate(
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
        val diffUtil = object : DiffUtil.ItemCallback<ArticleModel>(){
            override fun areItemsTheSame(oldItem: ArticleModel, newItem: ArticleModel): Boolean {
                return oldItem.articleId == newItem.articleId
            }

            override fun areContentsTheSame(oldItem: ArticleModel, newItem: ArticleModel): Boolean {
                return oldItem == newItem
            }

        }
    }
}