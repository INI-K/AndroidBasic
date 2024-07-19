package com.inik.todayhome.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.inik.todayhome.R
import com.inik.todayhome.data.ArticleModel
import com.inik.todayhome.databinding.ItemArtcleBinding

class HomeArticleAdapter(
    val onItemClicked: (ArticleItem) -> Unit,
    val onBookMarkClicked: (String, Boolean) -> Unit
) : ListAdapter<ArticleItem, HomeArticleAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder(private val binding: ItemArtcleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(articleModel: ArticleItem) {
            binding.thumnailTextView.text = articleModel.description

            Glide.with(binding.thumnailImageView)
                .load(articleModel.imageUrl)
                .into(binding.thumnailImageView)


            binding.root.setOnClickListener {
                onItemClicked(articleModel)
            }

            if (articleModel.isBookMark) {
                binding.bookmarkImageBtn.setBackgroundResource(R.drawable.baseline_bookmark_24)
            } else {
                binding.bookmarkImageBtn.setBackgroundResource(R.drawable.baseline_bookmark_border_24)
            }

            binding.bookmarkImageBtn.setOnClickListener {
                onBookMarkClicked.invoke(articleModel.articleId,articleModel.isBookMark.not())
                articleModel.isBookMark = articleModel.isBookMark.not()
                if (articleModel.isBookMark) {
                    binding.bookmarkImageBtn.setBackgroundResource(R.drawable.baseline_bookmark_24)
                } else {
                    binding.bookmarkImageBtn.setBackgroundResource(R.drawable.baseline_bookmark_border_24)
                }
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

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ArticleItem>() {
            override fun areItemsTheSame(oldItem: ArticleItem, newItem: ArticleItem): Boolean {
                return oldItem.articleId == newItem.articleId
            }

            override fun areContentsTheSame(oldItem: ArticleItem, newItem: ArticleItem): Boolean {
                return oldItem == newItem
            }

        }
    }
}