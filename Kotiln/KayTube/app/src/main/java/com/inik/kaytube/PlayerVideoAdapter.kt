package com.inik.kaytube

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewParent
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.inik.kaytube.databinding.ItemVideoBinding
import com.inik.kaytube.databinding.ItemVideoHeaderBinding

class PlayerVideoAdapter(private val context: Context,private var onClick: (VideoItem) -> Unit): ListAdapter<VideoItem,RecyclerView.ViewHolder>(diffUtil) {

    inner class HeaderViewHolder(private val binding: ItemVideoHeaderBinding, ): RecyclerView.ViewHolder(binding.root){
        fun bind(item: VideoItem){
            Log.e("확인", "연결됨?")
            binding.titleTextView.text = item.title
            binding.subTitleTextView.text = context.getString(R.string.header_sub_title_video_info,
                item.viewCount,
                item.dateText)
            binding.channelNameTextView.text = item.channelName
            binding.channelLogoImageView

            Glide.with(binding.channelLogoImageView)
                .load(item.channelThumb)
                .circleCrop()
                .into(binding.channelLogoImageView)

        }
    }

    inner class VideoViewHolder(private val binding: ItemVideoBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: VideoItem){
            binding.titleTextView.text = item.title
            binding.subTitleTextView.text = context.getString(R.string.sub_title_video_info,
                item.channelName,
                item.viewCount,
                item.dateText)

            Glide.with(binding.channelLogoImageView)
                .load(item.channelThumb)
                .circleCrop()
                .into(binding.channelLogoImageView)

            Glide.with(binding.videoThumnailImageView)
                .load(item.videoThumb)
                .into(binding.videoThumnailImageView)

            binding.root.setOnClickListener {
                onClick.invoke(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType == VIEW_TYPE_HEADER){
            HeaderViewHolder(
                ItemVideoHeaderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }else{
            VideoViewHolder(
                ItemVideoBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(getItemViewType(position) == VIEW_TYPE_HEADER){
            (holder as HeaderViewHolder).bind(currentList[position])
        }else{
            (holder as VideoViewHolder).bind(currentList[position])
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if(position == 0) VIEW_TYPE_HEADER else VIEW_TYPE_VIDEO
    }

    companion object{
        private const val  VIEW_TYPE_HEADER = 0
        private const val  VIEW_TYPE_VIDEO = 1

        val diffUtil = object : DiffUtil.ItemCallback<VideoItem>() {
            override fun areItemsTheSame(oldItem: VideoItem, newItem: VideoItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: VideoItem, newItem: VideoItem): Boolean {
                return oldItem == newItem
            }

        }
    }
}