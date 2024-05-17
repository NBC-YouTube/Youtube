package com.nbc.youtube.data.repository

import com.nbc.youtube.presentation.model.UserInfo
import com.nbc.youtube.presentation.model.VideoInfo

interface YoutubeRepository {

    fun getPopularVideos(): List<VideoInfo>
    fun getCategories(): List<String>
    fun getCategoryVideos(category: String): List<VideoInfo>
    suspend fun getFavoriteVideos(): List<VideoInfo>
    fun getSearchVideo(query: String, safeSearchType: String):List<VideoInfo>
    suspend fun addFavoriteVideo(video: VideoInfo)
    suspend fun removeFavoriteVideo(video: VideoInfo)

    suspend fun getUserInfo(): UserInfo
}
