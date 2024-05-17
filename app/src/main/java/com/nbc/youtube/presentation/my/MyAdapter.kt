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
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class MyAdapter(
    private val onClick: (VideoInfo) -> Unit,
) : ListAdapter<VideoInfo, MyAdapter.ViewHolder>(diff) {


    class ViewHolder(
        private val binding: ItemFavoriteVideoBinding,
        private val onClick: (VideoInfo) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {

        private lateinit var item: VideoInfo
        private val outputFormat = DateTimeFormatter.ofPattern("yyyy.MM.dd", Locale.KOREA)


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

            val releaseDateTime = parseReleaseDate(videoInfo.releaseDate)
            binding.tvReleaseDate.text = releaseDateTime
        }

        private fun parseReleaseDate(date: String): String {
            val dateTime = LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME)
            return dateTime.format(outputFormat)
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
        val diff = object : DiffUtil.ItemCallback<VideoInfo>() {
            override fun areItemsTheSame(oldItem: VideoInfo, newItem: VideoInfo): Boolean {
                return oldItem.thumbnail == newItem.thumbnail
            }

            override fun areContentsTheSame(oldItem: VideoInfo, newItem: VideoInfo): Boolean {
                return oldItem == newItem
            }

        }
    }
}