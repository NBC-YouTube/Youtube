package com.nbc.youtube.presentation.home.videmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nbc.youtube.data.repository.YoutubeRepository
import com.nbc.youtube.data.repository.testRepo

class CategoryViewModelFactory: ViewModelProvider.Factory {

    private val repository: YoutubeRepository = testRepo()

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CategoryViewModel::class.java)) {
            return CategoryViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}