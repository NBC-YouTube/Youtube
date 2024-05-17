package com.nbc.youtube.data.remote

import com.nbc.youtube.data.model.VideoEntity
import com.nbc.youtube.data.remote.model.CategoryEntity

interface YoutubeRemoteDataSource {
    suspend fun getPopularVideos(): List<VideoEntity>
    suspend fun getCategories(): List<CategoryEntity>
    suspend fun getCategoryVideos(categoryId: String): List<VideoEntity>
    suspend fun getSearchVideo(query: String, safeSearchType: String): List<VideoEntity>

}