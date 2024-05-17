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
                _searchVideos.value = videos.map {
                    val liked = it in favorites
                    VideoEntityWithLiked(
                        "2024-04-28T09:30:06Z",
                        "1",
                        "123",
                        "123",
                        "123",
                        "https://i.ytimg.com/vi/N9nsgoWuRoE/default.jpg",
                        "123",
                        liked
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