package com.nbc.youtube.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nbc.youtube.data.repository.YoutubeRepository
import com.nbc.youtube.data.repository.fakeRepo
import java.lang.IllegalArgumentException

class SearchViewModelFactory : ViewModelProvider.Factory {

    // 가짜 레포지토리 생성
     private val repository : YoutubeRepository = fakeRepo()

    override fun <T: ViewModel> create(modelClass: Class<T>) : T {
        if(modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            return SearchViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown viewModel Class")
    }
}