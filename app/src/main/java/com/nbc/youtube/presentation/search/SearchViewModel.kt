package com.nbc.youtube.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nbc.youtube.data.repository.YoutubeRepository
import com.nbc.youtube.presentation.search.model.VideoEntityWithLiked
import kotlinx.coroutines.launch

class SearchViewModel(
    private val repository: YoutubeRepository
) : ViewModel() {
    private val _searchVideos = MutableLiveData<List<VideoEntityWithLiked>>()
    val searchVideo: LiveData<List<VideoEntityWithLiked>>
        get() = _searchVideos

    fun loadSearchVideos(query: String, safeSearchType: String) {
        viewModelScope.launch {
            runCatching {
                val videos = repository.getSearchVideo(query, safeSearchType)
                val favorites = repository.getFavoriteVideos()
                _searchVideos.value = videos.map {video ->
                    val liked = favorites.any { it.id == video.id }
                    VideoEntityWithLiked(
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

        }
    }

    fun addFavoriteVideo(video: VideoEntityWithLiked) {
        val videos = _searchVideos.value?.toMutableList() ?: return
        videos.add(video)
        _searchVideos.value = videos
    }

    fun removeFavoriteVideo(video: VideoEntityWithLiked) {
        val videos = _searchVideos.value?.toMutableList() ?: return
        videos.remove(video)
        _searchVideos.value = videos
    }
}