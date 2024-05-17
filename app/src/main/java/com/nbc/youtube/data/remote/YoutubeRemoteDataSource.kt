package com.nbc.youtube.data.remote

import com.nbc.youtube.data.model.VideoEntity
import com.nbc.youtube.data.remote.model.CategoryEntity

interface YoutubeRemoteDataSource {
    suspend fun getPopularVideos(): List<VideoEntity>
    suspend fun getCategories(): List<CategoryEntity>
    fun getCategoryVideos(category: String): List<VideoEntity>
    fun getSearchVideo(query: String, safeSearchType: String): List<VideoEntity>

}