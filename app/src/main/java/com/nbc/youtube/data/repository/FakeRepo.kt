package com.nbc.youtube.data.repository

import android.net.Uri
import com.nbc.youtube.presentation.model.UserInfo
import com.nbc.youtube.presentation.model.VideoEntity
import com.nbc.youtube.presentation.search.model.VideoEntityWithLiked

class FakeRepo : YoutubeRepository {
    override fun getPopularVideos(): List<VideoEntity> {
        TODO()
    }

    override fun getCategories(): List<String> {
        TODO("Not yet implemented")
    }

    override fun getCategoryVideos(category: String): List<VideoEntity> {
        TODO("Not yet implemented")
    }

    override fun getFavoriteVideos(): List<VideoEntity> {
        return listOf(
            VideoEntity(
                releaseDate = "2024-04-28T09:30:06Z",
                id = "1",
                channelTitle = "1234",
                title = "menandri",
                description = "altera",
                thumbnail = "https://i.ytimg.com/vi/_HIe1nGVngo/maxresdefault.jpg",
                categoryId = "inimicus"
            ),
            VideoEntity(
                releaseDate = "2024-04-28T09:30:06Z",
                id = "2",
                channelTitle = "1234",
                title = "menandri",
                description = "altera",
                thumbnail = "https://i.ytimg.com/vi/K4QzrXhurN0/maxresdefault.jpg",
                categoryId = "inimicus"
            ),
            VideoEntity(
                releaseDate = "2024-04-28T09:30:06Z",
                id = "3",
                channelTitle = "1234",
                title = "menandri",
                description = "altera",
                thumbnail = "https://i.ytimg.com/vi/_HIe1nGVngo/maxresdefault.jpg",
                categoryId = "inimicus"
            ),
            VideoEntity(
                releaseDate = "2024-04-28T09:30:06Z",
                id = "4",
                channelTitle = "1234",
                title = "menandri",
                description = "altera",
                thumbnail = "https://i.ytimg.com/vi/K4QzrXhurN0/maxresdefault.jpg",
                categoryId = "inimicus"
            ),
            VideoEntity(
                releaseDate = "2024-04-28T09:30:06Z",
                id = "5",
                channelTitle = "1234",
                title = "menandri",
                description = "altera",
                thumbnail = "https://i.ytimg.com/vi/_HIe1nGVngo/maxresdefault.jpg",
                categoryId = "inimicus"
            ),
            VideoEntity(
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

    override fun getSearchVideo(query: String, safeSearchType: String): List<VideoEntityWithLiked> {
        return listOf(
            VideoEntityWithLiked(
                releaseDate = "2024-04-28T09:30:06Z",
                id = "1",
                channelTitle = "1234",
                title = "menandri",
                description = "altera",
                thumbnail = "https://i.ytimg.com/vi/_HIe1nGVngo/maxresdefault.jpg",
                categoryId = "inimicus",
                liked = false
            ),
            VideoEntityWithLiked(
                releaseDate = "2024-04-28T09:30:06Z",
                id = "2",
                channelTitle = "1234",
                title = "menandri",
                description = "altera",
                thumbnail = "https://i.ytimg.com/vi/K4QzrXhurN0/maxresdefault.jpg",
                categoryId = "inimicus",
                liked = true
            ),
            VideoEntityWithLiked(
                releaseDate = "2024-04-28T09:30:06Z",
                id = "3",
                channelTitle = "1234",
                title = "menandri",
                description = "altera",
                thumbnail = "https://i.ytimg.com/vi/_HIe1nGVngo/maxresdefault.jpg",
                categoryId = "inimicus",
                liked = false
            ),
            VideoEntityWithLiked(
                releaseDate = "2024-04-28T09:30:06Z",
                id = "4",
                channelTitle = "1234",
                title = "menandri",
                description = "altera",
                thumbnail = "https://i.ytimg.com/vi/K4QzrXhurN0/maxresdefault.jpg",
                categoryId = "inimicus",
                liked = false
            ),
            VideoEntityWithLiked(
                releaseDate = "2024-04-28T09:30:06Z",
                id = "5",
                channelTitle = "1234",
                title = "menandri",
                description = "altera",
                thumbnail = "https://i.ytimg.com/vi/_HIe1nGVngo/maxresdefault.jpg",
                categoryId = "inimicus",
                liked = false
            ),
            VideoEntityWithLiked(
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

    override fun addFavoriteVideo(video: VideoEntity) {
        TODO("Not yet implemented")
    }

    override fun removeFavoriteVideo(video: VideoEntity) {
        return
    }

    override fun getUserInfo(): UserInfo {
        TODO("Not yet implemented")
    }

}