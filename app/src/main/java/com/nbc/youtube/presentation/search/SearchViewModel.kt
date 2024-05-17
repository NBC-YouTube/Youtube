package com.nbc.youtube.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nbc.youtube.data.repository.YoutubeRepository
import com.nbc.youtube.presentation.model.VideoInfo
import com.nbc.youtube.presentation.search.model.VideoInfoWithLiked
import kotlinx.coroutines.launch

class SearchViewModel(
    private val repository: YoutubeRepository
) : ViewModel() {
    private val _searchVideos = MutableLiveData<List<VideoInfoWithLiked>>()
    val searchVideo: LiveData<List<VideoInfoWithLiked>>
        get() = _searchVideos

    private fun mapToVideoEntityWithLiked(
        videos: List<VideoInfoWithLiked>,
        favorites: List<VideoInfo>
    ): List<VideoInfoWithLiked> {
        return videos.map { video ->
            val liked = favorites.any { it.id == video.id }
            VideoInfoWithLiked(
                releaseDate = video.releaseDate,
                id = video.id,
                channelTitle = video.channelTitle,
                title = video.title,
                description = video.description,
                thumbnail = video.thumbnail,
                categoryId = video.categoryId,
                liked = liked
            )
        }
    }

    fun loadSearchVideos(query: String, safeSearchType: String) {
        viewModelScope.launch {
            runCatching {
                val videos = repository.getSearchVideo(query, safeSearchType)
                val favorites = repository.getFavoriteVideos()
                _searchVideos.value = mapToVideoEntityWithLiked(videos, favorites)
            }

        }
    }

    fun addFavoriteVideo(video: VideoInfoWithLiked) {
        val videos = _searchVideos.value?.toMutableList() ?: return
        videos.add(video)
        _searchVideos.value = videos
    }

    fun removeFavoriteVideo(video: VideoInfoWithLiked) {
        val videos = _searchVideos.value?.toMutableList() ?: return
        videos.remove(video)
        _searchVideos.value = videos
    }
}