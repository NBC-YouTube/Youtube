package com.nbc.youtube.data.remote

import com.nbc.youtube.data.local.model.VideoEntity

class YoutubeRemoteDataSourceImpl(
    private val youtubeService: YoutubeService,
): YoutubeRemoteDataSource {
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

    override fun getCategories(): List<String> {
        return emptyList()
    }

    override fun getCategoryVideos(category: String): List<VideoEntity> {
        TODO("Not yet implemented")
    }

    override fun getSearchVideo(query: String, safeSearchType: String): List<VideoEntity> {
        TODO("Not yet implemented")
    }
}