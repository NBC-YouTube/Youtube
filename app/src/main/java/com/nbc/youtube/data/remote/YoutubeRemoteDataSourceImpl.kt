package com.nbc.youtube.data.remote

import com.nbc.youtube.data.model.VideoEntity
import com.nbc.youtube.data.remote.model.CategoryEntity

class YoutubeRemoteDataSourceImpl(
    private val youtubeService: YoutubeService,
) : YoutubeRemoteDataSource {
    override suspend fun getPopularVideos(): List<VideoEntity> {
        return youtubeService.getPopularVideos().items?.map {
            VideoEntity(
                releaseDate = it.snippet?.publishedAt.orEmpty(),
                id = it.id.orEmpty(),
                channelTitle = it.snippet?.channelTitle.orEmpty(),
                title = it.snippet?.title.orEmpty(),
                description = it.snippet?.description.orEmpty(),
                thumbnail = it.snippet?.thumbnails?.maxres?.url.orEmpty(),
                categoryId = it.snippet?.categoryId.orEmpty(),
            )
        } ?: emptyList()
    }

    override suspend fun getCategories(): List<CategoryEntity> {
        return youtubeService.getCategories().items?.map {
            CategoryEntity(
                it.snippet?.title.orEmpty(),
                it.snippet?.categoryId.orEmpty(),
            )
        } ?: emptyList()
    }

    override fun getCategoryVideos(category: String): List<VideoEntity> {
        return emptyList()
    }

    override fun getSearchVideo(query: String, safeSearchType: String): List<VideoEntity> {
        return emptyList()
    }
}