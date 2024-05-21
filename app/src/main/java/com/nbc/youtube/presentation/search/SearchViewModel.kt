package com.nbc.youtube.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nbc.youtube.data.repository.YoutubeRepository
import com.nbc.youtube.presentation.model.VideoInfo
import com.nbc.youtube.presentation.search.model.SafeSearchType
import com.nbc.youtube.presentation.search.model.VideoInfoWithLiked
import kotlinx.coroutines.launch

class SearchViewModel(
    private val repository: YoutubeRepository
) : ViewModel() {
    private val _searchVideos = MutableLiveData<List<VideoInfoWithLiked>>()
    val searchVideo: LiveData<List<VideoInfoWithLiked>>
        get() = _searchVideos

    private val _kidsSearch = MutableLiveData(false)
    val kidSearch : LiveData<Boolean>
        get() = _kidsSearch

    private var selectedItem : VideoInfoWithLiked = VideoInfoWithLiked.EMPTY

    private fun mapToVideoEntityWithLiked(
        videos: List<VideoInfo>,
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

    fun loadSearchVideos(query: String, safeSearchType: SafeSearchType) {
        viewModelScope.launch {
            runCatching {
                val videos = repository.getSearchVideo(query, safeSearchType)
                val favorites = repository.getFavoriteVideos()
                _searchVideos.value = mapToVideoEntityWithLiked(videos, favorites)
            }

        }
    }

    fun updateLiked(video: VideoInfoWithLiked) {
        val newLiked = video.liked.not()

        viewModelScope.launch {
            if(newLiked) {
                repository.addFavoriteVideo(video.likedToVideoInfo())
            } else {
                repository.removeFavoriteVideo(video.likedToVideoInfo())
            }
            _searchVideos.value = _searchVideos.value?.map {
                if (it.id == video.id) it.copy(liked = newLiked) else it
            }
        }
    }

    fun kidsSearchType(kidsSearch: Boolean) {
        _kidsSearch.value = kidsSearch
    }

    fun updateSelectedItem(selectedItem: VideoInfoWithLiked) {
        this.selectedItem = selectedItem
    }

    fun onLikedChange(liked: Boolean) {
        if (selectedItem == VideoInfoWithLiked.EMPTY || selectedItem.liked == liked) {
            return
        }
        val videos = _searchVideos.value?.toMutableList() ?: return
        val idx = videos.indexOfFirst {
            it == selectedItem
        }
        if (idx == -1) {
            return
        }
        videos[idx] = selectedItem.copy(liked = liked)
        _searchVideos.value = videos
    }
}