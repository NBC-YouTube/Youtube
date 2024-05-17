package com.nbc.youtube.data.remote

import com.nbc.youtube.data.local.model.VideoEntity

interface YoutubeRemoteDataSource {
    suspend fun getPopularVideos(): List<VideoEntity>
    fun getCategories(): List<String>
    fun getCategoryVideos(category: String): List<VideoEntity>
    fun getSearchVideo(query: String, safeSearchType: String): List<VideoEntity>

}