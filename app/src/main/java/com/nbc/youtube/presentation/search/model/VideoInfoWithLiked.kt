package com.nbc.youtube.presentation.search.model

import com.nbc.youtube.presentation.model.VideoInfo

data class VideoInfoWithLiked(
    val releaseDate: String,
    val id: String,
    val channelTitle: String,
    val title: String,
    val description: String,
    val thumbnail: String,
    val categoryId: String,
    val liked: Boolean,
) {
    fun likedToVideoEntity() : VideoInfo {
        return VideoInfo(
            releaseDate = releaseDate,
            id = id,
            channelTitle = channelTitle,
            title = title,
            description = description,
            thumbnail = thumbnail,
            categoryId = categoryId,
        )
    }
}
