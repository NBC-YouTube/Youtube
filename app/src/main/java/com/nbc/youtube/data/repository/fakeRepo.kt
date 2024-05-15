package com.nbc.youtube.data.repository

import com.nbc.youtube.presentation.model.UserInfo
import com.nbc.youtube.presentation.model.VideoEntity

class fakeRepo : YoutubeRepository {
    override fun getPopularVideos(): List<VideoEntity> {
        TODO("Not yet implemented")
    }

    override fun getCategories(): List<String> {
        TODO("Not yet implemented")
    }

    override fun getCategoryVideos(category: String): List<VideoEntity> {
        TODO("Not yet implemented")
    }

    override fun getFavoriteVideos(): List<VideoEntity> {
        TODO("Not yet implemented")
    }

    override fun getSearchVideo(query: String, safeSearchType: String): List<VideoEntity> {
        TODO("Not yet implemented")
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