package com.nbc.youtube.presentation.my

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nbc.youtube.data.repository.YoutubeRepository
import com.nbc.youtube.presentation.model.UserInfo
import com.nbc.youtube.presentation.model.VideoInfo
import kotlinx.coroutines.launch

class MyViewModel(
    private val repository: YoutubeRepository,
): ViewModel() {

    private val _userInfo = MutableLiveData<UserInfo>()
    val userInfo: LiveData<UserInfo>
        get() = _userInfo

    private val _favoriteVideos = MutableLiveData<List<VideoInfo>>()
    val favoriteVideos: LiveData<List<VideoInfo>>
        get() = _favoriteVideos

    private var clickedVideo: VideoInfo? = null

    fun loadUserInfo() {
        viewModelScope.launch {
            runCatching {
                val info = repository.getUserInfo()
                _userInfo.value = info
            }
            runCatching {
                val videos = repository.getFavoriteVideos()
                _favoriteVideos.value = videos
            }
        }
    }

    fun removeFavoriteVideo() {
        val video = clickedVideo ?: return
        val videos = _favoriteVideos.value?.toMutableList() ?: return

        videos.remove(video)
        _favoriteVideos.value = videos
        clickedVideo = null
    }

    fun updateClickedItem(videoInfo: VideoInfo) {
        clickedVideo = videoInfo
    }
}