package com.nbc.youtube.presentation.my

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nbc.youtube.R
import com.nbc.youtube.databinding.ItemFavoriteVideoBinding
import com.nbc.youtube.presentation.model.VideoInfo

class MyAdapter(
    private val onClick: (VideoInfo) -> Unit,
): ListAdapter<VideoInfo, MyAdapter.ViewHolder>(diff) {


    class ViewHolder(
        private val binding: ItemFavoriteVideoBinding,
        private val onClick: (VideoInfo) -> Unit,
    ): RecyclerView.ViewHolder(binding.root) {

        private lateinit var item: VideoInfo

        init {
            binding.root.setOnClickListener {
                onClick(item)
            }
        }

        fun bind(videoInfo: VideoInfo) {
            item = videoInfo

            Glide.with(binding.root.context)
                .load(videoInfo.thumbnail)
                .placeholder(R.drawable.sample_placeholder)
                .into(binding.ivThumbnail)
            binding.tvChannelName.text = videoInfo.channelTitle
            binding.tvTitle.text = videoInfo.title
            binding.tvReleaseDate.text =videoInfo.releaseDate
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemFavoriteVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onClick,
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val diff = object: DiffUtil.ItemCallback<VideoInfo>() {
            override fun areItemsTheSame(oldItem: VideoInfo, newItem: VideoInfo): Boolean {
                return oldItem.thumbnail == newItem.thumbnail
            }

            override fun areContentsTheSame(oldItem: VideoInfo, newItem: VideoInfo): Boolean {
                return oldItem == newItem
            }

        }
    }
}