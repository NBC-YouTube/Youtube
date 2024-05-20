package com.nbc.youtube.presentation.home.videmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nbc.youtube.data.repository.YoutubeRepository

class VideosViewModelFactory: ViewModelProvider.Factory {

    private val repository: YoutubeRepository = YoutubeRepository()

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(VideosViewModel::class.java)) {
            return VideosViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}