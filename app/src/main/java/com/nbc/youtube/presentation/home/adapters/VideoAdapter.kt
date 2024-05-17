package com.nbc.youtube.presentation.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.nbc.youtube.R
import com.nbc.youtube.databinding.ItemHomeCategoryBinding
import com.nbc.youtube.databinding.ItemHomeMostPopularBinding
import com.nbc.youtube.presentation.model.VideoInfo

class VideoAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var itemList = listOf<VideoInfo>()
    private var videoType: Int = 1

    interface ItemClick {
        fun onClick(item: VideoInfo)
    }

    var itemClick: ItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            1 -> {
                val binding = ItemHomeMostPopularBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                Holder(binding)
            }
            2 -> {
                val binding = ItemHomeCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                Holder(binding)
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
            itemClick?.onClick(currentItem)
        }
        (holder as Holder).bind(currentItem)
    }

    class Holder(private val binding: ViewBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: VideoInfo) {
            Glide.with(binding.root)
                .load(item.thumbnail)
                .apply(RequestOptions().centerCrop().transform(RoundedCorners(25)))
                .into(binding.root.findViewById(R.id.iv_thumbnail))
            binding.root.findViewById<TextView>(R.id.tv_title).text = item.title
        }
    }

    fun listUpdate(item: List<VideoInfo>, viewType: Int) {
        this.itemList = item
        this.videoType = viewType
        notifyDataSetChanged()
    }

}