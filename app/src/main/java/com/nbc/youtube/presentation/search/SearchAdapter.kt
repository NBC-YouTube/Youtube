package com.nbc.youtube.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nbc.youtube.R
import com.nbc.youtube.databinding.SearchItemBinding
import com.nbc.youtube.presentation.model.VideoEntity

class SearchAdapter(
    private val onClick: (VideoEntity) -> Unit,
) : ListAdapter<VideoEntity, SearchAdapter.ViewHolder>(diff) {

    class ViewHolder(
        private val binding: SearchItemBinding,
        private val onClick: (VideoEntity) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {

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
                .placeholder(R.drawable.ic_reload) // 로드 실패 시
                .into(binding.searchThumbnail)
            binding.searchTitle.text = videoEntity.title
            binding.searchReleaseDate.text = videoEntity.releaseDate
            binding.searchChannelTitle.text = videoEntity.channelTitle
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            SearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onClick,
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val diff = object : DiffUtil.ItemCallback<VideoEntity>() {
            override fun areItemsTheSame(oldItem: VideoEntity, newItem: VideoEntity): Boolean {
                return oldItem.thumbnail == newItem.thumbnail
            }

            override fun areContentsTheSame(oldItem: VideoEntity, newItem: VideoEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

}