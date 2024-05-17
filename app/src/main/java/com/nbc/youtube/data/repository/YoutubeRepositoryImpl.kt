package com.nbc.youtube.data.repository

import com.nbc.youtube.data.local.VideoEntityDao
import com.nbc.youtube.data.remote.YoutubeRemoteDataSource
import com.nbc.youtube.presentation.model.UserInfo
import com.nbc.youtube.presentation.model.VideoInfo

class YoutubeRepositoryImpl(
    private val youtubeRemoteDataSource: YoutubeRemoteDataSource,
    private val videoEntityDao: VideoEntityDao,
) : YoutubeRepository {
    override fun getPopularVideos(): List<VideoInfo> {
        TODO("Not yet implemented")
    }

    override fun getCategories(): List<String> {
        TODO("Not yet implemented")
    }

    override fun getCategoryVideos(category: String): List<VideoInfo> {
        TODO("Not yet implemented")
    }

    override suspend fun getFavoriteVideos(): List<VideoInfo> {
        return videoEntityDao.getFavoriteVideos().map {
            it.toPresentation()
        }
    }

    override fun getSearchVideo(query: String, safeSearchType: String): List<VideoInfo> {
        TODO("Not yet implemented")
    }

    override suspend fun addFavoriteVideo(video: VideoInfo) {
        videoEntityDao.addFavoriteVideo(video.toEntity())
    }

    override suspend fun removeFavoriteVideo(video: VideoInfo) {
        videoEntityDao.removeFavoriteVideo(video.toEntity())
    }

    override fun getUserInfo(): UserInfo {
        TODO("Not yet implemented")
    }

}