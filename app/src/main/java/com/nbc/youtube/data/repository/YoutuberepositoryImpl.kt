package com.nbc.youtube.data.repository

import com.nbc.youtube.data.remote.network.YouTubeSearchResponse
import com.nbc.youtube.data.remote.network.YouTubeService
import com.nbc.youtube.presentation.model.UserInfo
import com.nbc.youtube.presentation.model.VideoEntity
import retrofit2.Call

// YoutubeRepository의 구현체
// API와의 통신을 담당
class YouTubeRepositoryImpl(private val youTubeService: YouTubeService) : YoutubeRepository  {
    override suspend fun getPopularVideos(
        apiKey: String,
        regionCode: String
    ): List<VideoEntity> {
        val response = youTubeService.getPopularVideos(part = "snippet", chart = "mostPopular", regionCode = regionCode, apiKey = apiKey, maxResults = 20)
        return response.items
    }

    override fun getCategories(): List<String> {
        TODO("Not yet implemented")
    }

    override suspend fun getCategoryVideos(apiKey: String, category: String, regionCode: String): List<VideoEntity> {
        val response = youTubeService.getCategoryVideos(part = "snippet", chart = "mostPopular", regionCode = "KR", videoCategoryId = "10", apiKey = apiKey, maxResults = 20)
        return response.items
    }

    override fun getFavoriteVideos(): List<VideoEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun searchVideos(query: String, apiKey: String): List<VideoEntity> {
        val response = youTubeService.getSearchVideo(part = "snippet", order = "date", query = query, apiKey = apiKey, type = "video", safeSearch = "strict", maxResults = 20)
        return response.items
    }

    override fun addFavoriteVideo(video: VideoEntity) {
        TODO("Not yet implemented")
    }

    override fun removeFavoriteVideo(video: VideoEntity) {
        TODO("Not yet implemented")
    }

    override fun getUserInfo(): UserInfo {
        TODO("Not yet implemented")
    }

}