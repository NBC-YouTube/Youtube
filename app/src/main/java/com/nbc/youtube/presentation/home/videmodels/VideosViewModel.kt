package com.nbc.youtube.presentation.home.videmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nbc.youtube.data.repository.YoutubeRepository
import com.nbc.youtube.presentation.model.VideoEntity
import kotlinx.coroutines.launch

class VideosViewModel(private val repository: YoutubeRepository) : ViewModel() {
    private val _popularVideos = MutableLiveData<List<VideoEntity>>()
    val popularVideos : LiveData<List<VideoEntity>> get() = _popularVideos

    private val _categoryVideos = MutableLiveData<List<VideoEntity>>()
    val categoryVideos : LiveData<List<VideoEntity>> get() = _categoryVideos

    fun loadPopularVideos() {
        viewModelScope.launch {
            runCatching {
                val videos = repository.getPopularVideos()
                _popularVideos.value = videos
            }
        }
    }

    fun loadCategoryVideos(category: String) {
        viewModelScope.launch {
            runCatching {
                val videos = repository.getCategoryVideos(category)
                _categoryVideos.value = videos
            }
        }
    }
}