package com.nbc.youtube.presentation.search.model

import com.nbc.youtube.presentation.model.VideoInfo

data class VideoInfoWithLiked(
    val releaseDate: String,
    var id: String,
    val channelTitle: String,
    val title: String,
    val description: String,
    val thumbnail: String,
    val categoryId: String,
    val liked: Boolean,
) {
    fun likedToVideoInfo(): VideoInfo {
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

    companion object {
        val EMPTY = VideoInfoWithLiked(
            releaseDate = "",
            id = "",
            channelTitle = "",
            title = "",
            description = "",
            thumbnail = "",
            categoryId = "",
            liked = false
        )
    }
}
