package com.nbc.youtube.data.repository

import com.nbc.youtube.presentation.model.UserInfo
import com.nbc.youtube.presentation.model.VideoInfo
import com.nbc.youtube.presentation.search.model.VideoInfoWithLiked

interface YoutubeRepository {

    fun getPopularVideos(): List<VideoInfo>
    fun getCategories(): List<String>
    fun getCategoryVideos(category: String): List<VideoInfo>
    fun getFavoriteVideos(): List<VideoInfo>
    fun getSearchVideo(query: String, safeSearchType: String):List<VideoInfoWithLiked>
    fun addFavoriteVideo(video: VideoInfo)
    fun removeFavoriteVideo(video: VideoInfo)

    fun getUserInfo(): UserInfo
}
