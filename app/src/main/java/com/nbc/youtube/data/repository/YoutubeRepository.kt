package com.nbc.youtube.data.repository

import com.nbc.youtube.presentation.model.CategoryInfo
import com.nbc.youtube.presentation.model.UserInfo
import com.nbc.youtube.presentation.model.VideoInfo

interface YoutubeRepository {

    suspend fun getPopularVideos(): List<VideoInfo>
    suspend fun getCategories(): List<CategoryInfo>
    fun getCategoryVideos(category: String): List<VideoInfo>
    suspend fun getFavoriteVideos(): List<VideoInfo>
    fun getSearchVideo(query: String, safeSearchType: String): List<VideoInfo>
    suspend fun addFavoriteVideo(video: VideoInfo)
    suspend fun removeFavoriteVideo(video: VideoInfo)

    suspend fun getUserInfo(): UserInfo
}
