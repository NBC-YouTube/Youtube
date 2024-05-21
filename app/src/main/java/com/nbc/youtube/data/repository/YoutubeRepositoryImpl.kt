package com.nbc.youtube.data.repository

import com.nbc.youtube.data.local.UserEntityDao
import com.nbc.youtube.data.local.VideoEntityDao
import com.nbc.youtube.data.local.model.UserEntity
import com.nbc.youtube.data.remote.YoutubeRemoteDataSource
import com.nbc.youtube.presentation.model.CategoryInfo
import com.nbc.youtube.presentation.model.UserInfo
import com.nbc.youtube.presentation.model.VideoInfo
import com.nbc.youtube.presentation.search.model.SafeSearchType

class YoutubeRepositoryImpl(
    private val youtubeRemoteDataSource: YoutubeRemoteDataSource,
    private val videoEntityDao: VideoEntityDao,
    private val userEntityDao: UserEntityDao,
) : YoutubeRepository {
    override suspend fun getPopularVideos(): List<VideoInfo> {
        return youtubeRemoteDataSource.getPopularVideos().map {
            it.toPresentation()
        }
    }

    override suspend fun getCategories(): List<CategoryInfo> {
        return youtubeRemoteDataSource.getCategories().map { it.toPresentation() }
    }

    override suspend fun getCategoryVideos(categoryId: String): List<VideoInfo> {
        return youtubeRemoteDataSource.getCategoryVideos(categoryId).map {
            it.toPresentation()
        }
    }

    override suspend fun getFavoriteVideos(): List<VideoInfo> {
        return videoEntityDao.getFavoriteVideos().map {
            it.toPresentation()
        }
    }

    override suspend fun getSearchVideo(
        query: String,
        safeSearchType: SafeSearchType
    ): List<VideoInfo> {
        return youtubeRemoteDataSource.getSearchVideo(query, safeSearchType.symbol).map {
            it.toPresentation()
        }
    }

    override suspend fun addFavoriteVideo(video: VideoInfo) {
        videoEntityDao.addFavoriteVideo(video.toEntity())
    }

    override suspend fun removeFavoriteVideo(video: VideoInfo) {
        videoEntityDao.removeFavoriteVideo(video.toEntity())
    }

    override suspend fun getUserInfo(): UserInfo {
        return userEntityDao.getUserEntity(UserEntity.DEFAULT_USER_ID).toPresentation()
    }

}