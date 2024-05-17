package com.nbc.youtube.presentation.search

import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nbc.youtube.R
import com.nbc.youtube.databinding.SearchItemBinding
import com.nbc.youtube.presentation.search.model.VideoInfoWithLiked
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

class SearchAdapter(
    private val onClick: (VideoInfoWithLiked) -> Unit,
    private val likeClick: (VideoInfoWithLiked) -> Unit,
) : ListAdapter<VideoInfoWithLiked, SearchAdapter.ViewHolder>(diff) {

    class ViewHolder(
        private val binding: SearchItemBinding,
        private val onClick: (VideoInfoWithLiked) -> Unit,
        private val likeClick: (VideoInfoWithLiked) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {

        private lateinit var item: VideoInfoWithLiked

        init {
            binding.root.setOnClickListener {
                onClick(item)
            }

            binding.searchLikeBtn.setOnClickListener {
                item = item.copy(liked = !item.liked)
                updateLikeButton()
                likeClick(item)
            }
        }

        private fun updateLikeButton() {
            val imageResource =
                if (item.liked) R.drawable.ic_thumb_fill else R.drawable.ic_thumb_empty
            Glide.with(binding.root.context)
                .load(imageResource)
                .into(binding.searchLikeBtn)
        }

        fun bind(videoEntity: VideoInfoWithLiked) {
            item = videoEntity

            Glide.with(binding.root.context)
                .load(videoEntity.thumbnail)
                .placeholder(R.drawable.ic_reload) // 로드 실패 시
                .into(binding.searchThumbnail)
            binding.searchTitle.text = videoEntity.title
            binding.searchChannelTitle.text = videoEntity.channelTitle

            val formattedDate = try {
                val parsedDate = LocalDate.parse(videoEntity.releaseDate)
                parsedDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"))
            } catch (e: DateTimeParseException) {
                videoEntity.releaseDate
            }

            binding.searchReleaseDate.text = formattedDate
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            SearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onClick,
            likeClick
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val diff = object : DiffUtil.ItemCallback<VideoInfoWithLiked>() {
            override fun areItemsTheSame(
                oldItem: VideoInfoWithLiked,
                newItem: VideoInfoWithLiked
            ): Boolean {
                return oldItem.thumbnail == newItem.thumbnail
            }

            override fun areContentsTheSame(
                oldItem: VideoInfoWithLiked,
                newItem: VideoInfoWithLiked
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

}