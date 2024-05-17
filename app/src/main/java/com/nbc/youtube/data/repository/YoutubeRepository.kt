package com.nbc.youtube.data.repository

import com.nbc.youtube.presentation.model.UserInfo
import com.nbc.youtube.presentation.model.VideoEntity
import com.nbc.youtube.presentation.search.model.VideoEntityWithLiked

interface YoutubeRepository {

    fun getPopularVideos(): List<VideoEntity>
    fun getCategories(): List<String>
    fun getCategoryVideos(category: String): List<VideoEntity>
    fun getFavoriteVideos(): List<VideoEntity>
    fun getSearchVideo(query: String, safeSearchType: String):List<VideoEntityWithLiked>
    fun addFavoriteVideo(video: VideoEntity)
    fun removeFavoriteVideo(video: VideoEntity)

    fun getUserInfo(): UserInfo
}
