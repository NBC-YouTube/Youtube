package com.nbc.youtube.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nbc.youtube.data.repository.YoutubeRepository
import java.lang.IllegalArgumentException

class SearchViewModelFactory : ViewModelProvider.Factory {

     private val repository : YoutubeRepository = YoutubeRepository()

    override fun <T: ViewModel> create(modelClass: Class<T>) : T {
        if(modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            return SearchViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown viewModel Class")
    }
}