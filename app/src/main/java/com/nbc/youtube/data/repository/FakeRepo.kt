package com.nbc.youtube.data.repository

import com.nbc.youtube.presentation.model.UserInfo
import com.nbc.youtube.presentation.model.VideoInfo
import com.nbc.youtube.presentation.search.model.VideoInfoWithLiked

class FakeRepo : YoutubeRepository {
    override fun getPopularVideos(): List<VideoInfo> {
        TODO()
    }

    override fun getCategories(): List<String> {
        TODO("Not yet implemented")
    }

    override fun getCategoryVideos(category: String): List<VideoInfo> {
        TODO("Not yet implemented")
    }

    override fun getFavoriteVideos(): List<VideoInfo> {
        return listOf(
            VideoInfo(
                releaseDate = "2024-04-28T09:30:06Z",
                id = "1",
                channelTitle = "1234",
                title = "menandri",
                description = "altera",
                thumbnail = "https://i.ytimg.com/vi/_HIe1nGVngo/maxresdefault.jpg",
                categoryId = "inimicus"
            ),
            VideoInfo(
                releaseDate = "2024-04-28T09:30:06Z",
                id = "2",
                channelTitle = "1234",
                title = "menandri",
                description = "altera",
                thumbnail = "https://i.ytimg.com/vi/K4QzrXhurN0/maxresdefault.jpg",
                categoryId = "inimicus"
            ),
            VideoInfo(
                releaseDate = "2024-04-28T09:30:06Z",
                id = "3",
                channelTitle = "1234",
                title = "menandri",
                description = "altera",
                thumbnail = "https://i.ytimg.com/vi/_HIe1nGVngo/maxresdefault.jpg",
                categoryId = "inimicus"
            ),
            VideoInfo(
                releaseDate = "2024-04-28T09:30:06Z",
                id = "4",
                channelTitle = "1234",
                title = "menandri",
                description = "altera",
                thumbnail = "https://i.ytimg.com/vi/K4QzrXhurN0/maxresdefault.jpg",
                categoryId = "inimicus"
            ),
            VideoInfo(
                releaseDate = "2024-04-28T09:30:06Z",
                id = "5",
                channelTitle = "1234",
                title = "menandri",
                description = "altera",
                thumbnail = "https://i.ytimg.com/vi/_HIe1nGVngo/maxresdefault.jpg",
                categoryId = "inimicus"
            ),
            VideoInfo(
                releaseDate = "2024-04-28T09:30:06Z",
                id = "6",
                channelTitle = "1234",
                title = "menandri",
                description = "altera",
                thumbnail = "https://i.ytimg.com/vi/K4QzrXhurN0/maxresdefault.jpg",
                categoryId = "inimicus"
            ),

            )
    }

    override fun getSearchVideo(query: String, safeSearchType: String): List<VideoInfoWithLiked> {
        return listOf(
            VideoInfoWithLiked(
                releaseDate = "2024-04-28T09:30:06Z",
                id = "1",
                channelTitle = "1234",
                title = "menandri",
                description = "altera",
                thumbnail = "https://i.ytimg.com/vi/_HIe1nGVngo/maxresdefault.jpg",
                categoryId = "inimicus",
                liked = false
            ),
            VideoInfoWithLiked(
                releaseDate = "2024-04-28T09:30:06Z",
                id = "2",
                channelTitle = "1234",
                title = "menandri",
                description = "altera",
                thumbnail = "https://i.ytimg.com/vi/K4QzrXhurN0/maxresdefault.jpg",
                categoryId = "inimicus",
                liked = true
            ),
            VideoInfoWithLiked(
                releaseDate = "2024-04-28T09:30:06Z",
                id = "3",
                channelTitle = "1234",
                title = "menandri",
                description = "altera",
                thumbnail = "https://i.ytimg.com/vi/_HIe1nGVngo/maxresdefault.jpg",
                categoryId = "inimicus",
                liked = false
            ),
            VideoInfoWithLiked(
                releaseDate = "2024-04-28T09:30:06Z",
                id = "4",
                channelTitle = "1234",
                title = "menandri",
                description = "altera",
                thumbnail = "https://i.ytimg.com/vi/K4QzrXhurN0/maxresdefault.jpg",
                categoryId = "inimicus",
                liked = false
            ),
            VideoInfoWithLiked(
                releaseDate = "2024-04-28T09:30:06Z",
                id = "5",
                channelTitle = "1234",
                title = "menandri",
                description = "altera",
                thumbnail = "https://i.ytimg.com/vi/_HIe1nGVngo/maxresdefault.jpg",
                categoryId = "inimicus",
                liked = false
            ),
            VideoInfoWithLiked(
                releaseDate = "2024-04-28T09:30:06Z",
                id = "6",
                channelTitle = "1234",
                title = "menandri",
                description = "altera",
                thumbnail = "https://i.ytimg.com/vi/K4QzrXhurN0/maxresdefault.jpg",
                categoryId = "inimicus",
                liked = false
            ),

            )
    }

    override fun addFavoriteVideo(video: VideoInfo) {
        TODO("Not yet implemented")
    }

    override fun removeFavoriteVideo(video: VideoInfo) {
        return
    }

    override fun getUserInfo(): UserInfo {
        TODO("Not yet implemented")
    }

}