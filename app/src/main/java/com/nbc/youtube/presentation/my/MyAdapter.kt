package com.nbc.youtube.presentation.my

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nbc.youtube.R
import com.nbc.youtube.databinding.ItemFavoriteVideoBinding
import com.nbc.youtube.presentation.model.VideoEntity

class MyAdapter(
    private val onClick: (VideoEntity) -> Unit,
): ListAdapter<VideoEntity, MyAdapter.ViewHolder>(diff) {


    class ViewHolder(
        private val binding: ItemFavoriteVideoBinding,
        private val onClick: (VideoEntity) -> Unit,
    ): RecyclerView.ViewHolder(binding.root) {

        private lateinit var item: VideoEntity

        init {
            binding.root.setOnClickListener {
                onClick(item)
            }
        }

        fun bind(videoEntity: VideoEntity) {
            item = videoEntity

            Glide.with(binding.root.context)
                .load(videoEntity.thumbnail)
                .placeholder(R.drawable.sample_placeholder)
                .into(binding.ivThumbnail)
            binding.tvChannelName.text = videoEntity.channelTitle
            binding.tvTitle.text = videoEntity.title
            binding.tvReleaseDate.text =videoEntity.releaseDate
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
        val diff = object: DiffUtil.ItemCallback<VideoEntity>() {
            override fun areItemsTheSame(oldItem: VideoEntity, newItem: VideoEntity): Boolean {
                return oldItem.thumbnail == newItem.thumbnail
            }

            override fun areContentsTheSame(oldItem: VideoEntity, newItem: VideoEntity): Boolean {
                return oldItem == newItem
            }

        }
    }
}