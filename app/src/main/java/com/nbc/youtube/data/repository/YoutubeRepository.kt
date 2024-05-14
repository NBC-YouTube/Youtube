package com.nbc.youtube.data.repository

import com.nbc.youtube.data.remote.network.YouTubeSearchResponse
import com.nbc.youtube.presentation.model.UserInfo
import com.nbc.youtube.presentation.model.VideoEntity

interface YoutubeRepository {

    // 인기 영상 순위
    suspend fun getPopularVideos(apiKey: String, regionCode: String = "KR"): List<VideoEntity>
    fun getCategories(): List<String>
    // 카테고리별 영상
    suspend fun getCategoryVideos(apiKey: String, category: String, regionCode: String = "KR"): List<VideoEntity>
    fun getFavoriteVideos(): List<VideoEntity>
    // 검색
    suspend fun searchVideos(query: String, apiKey: String): List<VideoEntity>
    fun addFavoriteVideo(video: VideoEntity)
    fun removeFavoriteVideo(video: VideoEntity)

    fun getUserInfo(): UserInfo
}
