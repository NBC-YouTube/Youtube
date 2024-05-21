package com.nbc.youtube.data.repository

import com.nbc.youtube.presentation.model.CategoryInfo
import com.nbc.youtube.presentation.model.UserInfo
import com.nbc.youtube.presentation.model.VideoInfo
import com.nbc.youtube.presentation.search.model.SafeSearchType

interface YoutubeRepository {

    suspend fun getPopularVideos(): List<VideoInfo>
    suspend fun getCategories(): List<CategoryInfo>
    suspend fun getCategoryVideos(categoryId: String): List<VideoInfo>
    suspend fun getFavoriteVideos(): List<VideoInfo>
    suspend fun getSearchVideo(query: String, safeSearchType: SafeSearchType): List<VideoInfo>
    suspend fun addFavoriteVideo(video: VideoInfo)
    suspend fun removeFavoriteVideo(video: VideoInfo)

    suspend fun getUserInfo(): UserInfo
}
