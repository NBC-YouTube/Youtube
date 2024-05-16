package com.nbc.youtube.presentation.my

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nbc.youtube.data.repository.FakeRepositoryImpl
import com.nbc.youtube.data.repository.YoutubeRepository

class MyViewModelFactory: ViewModelProvider.Factory {

    private val repository: YoutubeRepository = FakeRepositoryImpl()

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MyViewModel(
            repository
        ) as T
    }
}