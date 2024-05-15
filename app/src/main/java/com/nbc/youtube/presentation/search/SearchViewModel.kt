package com.nbc.youtube.presentation.search

import android.provider.MediaStore.Video
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nbc.youtube.data.repository.YoutubeRepository
import com.nbc.youtube.presentation.model.VideoEntity
import kotlinx.coroutines.launch

class SearchViewModel(
    private val repository: YoutubeRepository) : ViewModel() {

    private val _favoriteVideos = MutableLiveData<List<VideoEntity>>()
    val favoriteVideos: LiveData<List<VideoEntity>>
        get() = _favoriteVideos

    private val _searchVideos = MutableLiveData<List<VideoEntity>>()
    val searchVideo: LiveData<List<VideoEntity>>
        get() = _searchVideos

    fun loadSearchVideos(query: String, safeSearchType: String) {
        viewModelScope.launch {
            runCatching {
                val videos = repository.getSearchVideo(query, safeSearchType)
                _searchVideos.value = videos
            }

            runCatching {
                val videos = repository.getFavoriteVideos()
                _favoriteVideos.value = videos
            }
        }
    }
}