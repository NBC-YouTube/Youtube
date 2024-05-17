package com.nbc.youtube.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nbc.youtube.data.repository.YoutubeRepository
import com.nbc.youtube.presentation.model.VideoInfo
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: YoutubeRepository,
) : ViewModel() {

    private val _videoInfo = MutableLiveData<VideoInfo>()
    val videoInfo: LiveData<VideoInfo>
        get() = _videoInfo

    private val _isLiked = MutableLiveData<Boolean>()
    val isLiked: LiveData<Boolean>
        get() = _isLiked

    private val _finalLiked = MutableLiveData<Boolean>()
    val finalLiked: LiveData<Boolean>
        get() = _finalLiked

    fun setData(videoInfo: VideoInfo) {
        _videoInfo.value = videoInfo

        viewModelScope.launch {
            val likedVideos = repository.getFavoriteVideos()
            _isLiked.value = videoInfo in likedVideos
        }
    }

    fun updateLiked() {
        val liked = _isLiked.value?.not() ?: return
        val videoInfo = _videoInfo.value ?: return

        _isLiked.value = liked

        viewModelScope.launch {
           if (liked) {
               repository.addFavoriteVideo(videoInfo)
           } else {
               repository.removeFavoriteVideo(videoInfo)
           }
        }
    }

    fun readyForPop() {
        _finalLiked.value = _isLiked.value
    }
}