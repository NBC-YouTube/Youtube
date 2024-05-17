package com.nbc.youtube.data.repository

import android.net.Uri
import com.nbc.youtube.presentation.model.UserInfo
import com.nbc.youtube.presentation.model.VideoInfo
import com.nbc.youtube.presentation.search.model.VideoInfoWithLiked

class FakeRepositoryImpl : YoutubeRepository {
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
                releaseDate = "mandamus",
                id = "1",
                channelTitle = "1234",
                title = "menandri",
                description = "altera",
                thumbnail = "https://i.ytimg.com/vi/_HIe1nGVngo/maxresdefault.jpg",
                categoryId = "inimicus"
            ),
            VideoInfo(
                releaseDate = "mandamus",
                id = "2",
                channelTitle = "1234",
                title = "menandri",
                description = "altera",
                thumbnail = "https://i.ytimg.com/vi/K4QzrXhurN0/maxresdefault.jpg",
                categoryId = "inimicus"
            ),
            VideoInfo(
                releaseDate = "mandamus",
                id = "3",
                channelTitle = "1234",
                title = "menandri",
                description = "altera",
                thumbnail = "https://i.ytimg.com/vi/_HIe1nGVngo/maxresdefault.jpg",
                categoryId = "inimicus"
            ),
            VideoInfo(
                releaseDate = "mandamus",
                id = "4",
                channelTitle = "1234",
                title = "menandri",
                description = "altera",
                thumbnail = "https://i.ytimg.com/vi/K4QzrXhurN0/maxresdefault.jpg",
                categoryId = "inimicus"
            ),
            VideoInfo(
                releaseDate = "mandamus",
                id = "5",
                channelTitle = "1234",
                title = "menandri",
                description = "altera",
                thumbnail = "https://i.ytimg.com/vi/_HIe1nGVngo/maxresdefault.jpg",
                categoryId = "inimicus"
            ),
            VideoInfo(
                releaseDate = "mandamus",
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
        TODO("Not yet implemented")
    }

    override fun addFavoriteVideo(video: VideoInfo) {
        TODO("Not yet implemented")
    }

    override fun removeFavoriteVideo(video: VideoInfo) {
        return
    }

    override fun getUserInfo(): UserInfo {
        return UserInfo(
            name = "My PoKemon Channel Dummy Dummy",
            profileThumbnail = Uri.parse("android.resource://com.nbc.youtube/drawable/sample_user_thumbnail"),
            channelThumbnail = Uri.parse("android.resource://com.nbc.youtube/drawable/sample_channel_thumbnail"),
            introduction = "Discover the latest Pokémon news, animations, and gameplay on the official Pokémon YouTube channel. Dive into the captivating world of Pokémon and experience everything it has to offer right here!",
        )
    }

}