package com.nbc.youtube.presentation.search.model

data class VideoEntityWithLiked(
    val releaseDate: String,
    val id: String,
    val channelTitle: String,
    val title: String,
    val description: String,
    val thumbnail: String,
    val categoryId: String,
    val liked: Boolean,
)
