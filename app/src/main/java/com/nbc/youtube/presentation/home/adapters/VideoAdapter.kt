package com.nbc.youtube.presentation.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.nbc.youtube.databinding.ItemHomeCategoryBinding
import com.nbc.youtube.databinding.ItemHomeMostPopularBinding
import com.nbc.youtube.presentation.model.VideoEntity

class VideoAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var itemList = listOf<VideoEntity>()
    var videoType: Int = 1

    interface ItemClick {
        fun onClick(view: View, position: Int)
    }

    var itemClick: ItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            1 -> {
                val binding = ItemHomeMostPopularBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                popularHolder(binding)
            }
            2 -> {
                val binding = ItemHomeCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                categoryHolder(binding)
            }
            else -> throw IllegalArgumentException("Unknown View Type")
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun getItemViewType(position: Int): Int {
        return videoType
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.itemView.setOnClickListener {
            itemClick?.onClick(it,position)
        }
        when(holder.itemViewType) {
            1 -> {
                (holder as popularHolder).bind(currentItem)
            }
            2 -> {
                (holder as categoryHolder).bind(currentItem)
            }
        }
    }

    class popularHolder(private val binding: ItemHomeMostPopularBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: VideoEntity) {
            Glide.with(binding.root)
                .load(item.thumbnail)
                .apply(RequestOptions().centerCrop().transform(RoundedCorners(25)))
                .into(binding.ivThumbnail)
            binding.tvTitle.text = item.title
        }
    }

    class categoryHolder(private val binding: ItemHomeCategoryBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: VideoEntity) {
            Glide.with(binding.root)
                .load(item.thumbnail)
                .apply(RequestOptions().centerCrop().transform(RoundedCorners(25)))
                .into(binding.ivCategoryThumbnail)
            binding.tvCategoryTitle.text = item.title
        }
    }

    fun listUpdate(item: List<VideoEntity>, viewType: Int) {
        this.itemList = item
        this.videoType = viewType
        notifyDataSetChanged()
    }

}