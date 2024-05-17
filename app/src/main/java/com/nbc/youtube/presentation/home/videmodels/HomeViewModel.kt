package com.nbc.youtube.presentation.home.videmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nbc.youtube.data.repository.YoutubeRepository
import com.nbc.youtube.presentation.model.VideoInfo
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: YoutubeRepository) : ViewModel() {
    private val _popularVideos = MutableLiveData<List<VideoInfo>>()
    val popularVideos: LiveData<List<VideoInfo>> get() = _popularVideos

    private val _categoryVideos = MutableLiveData<List<VideoInfo>>()
    val categoryVideos: LiveData<List<VideoInfo>> get() = _categoryVideos

    private val _categories = MutableLiveData<List<String>>()
    val categories: LiveData<List<String>> get() = _categories
    fun loadCategories() {
        viewModelScope.launch {
            _categories.value = repository.getCategories()
        }
    }

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