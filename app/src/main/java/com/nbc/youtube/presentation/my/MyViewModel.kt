package com.nbc.youtube.presentation.my

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nbc.youtube.data.repository.YoutubeRepository
import com.nbc.youtube.presentation.model.UserInfo
import kotlinx.coroutines.launch

class MyViewModel(
    private val repository: YoutubeRepository,
): ViewModel() {

    private val _userInfo = MutableLiveData<UserInfo>()
    val userInfo: LiveData<UserInfo>
        get() = _userInfo

    fun loadUserInfo() {
        viewModelScope.launch {
            runCatching {
                val info = repository.getUserInfo()
                _userInfo.value = info
            }
        }
    }
}