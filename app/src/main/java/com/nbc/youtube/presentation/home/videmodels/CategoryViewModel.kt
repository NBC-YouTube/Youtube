package com.nbc.youtube.presentation.home.videmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nbc.youtube.data.repository.YoutubeRepository
import kotlinx.coroutines.launch

class CategoryViewModel(private val repository: YoutubeRepository): ViewModel() {
    private val _categories = MutableLiveData<List<String>>()
    val categories : LiveData<List<String>> get() =_categories
    fun loadCategories() {
        viewModelScope.launch {
            _categories.value = repository.getCategories()
        }
    }
}