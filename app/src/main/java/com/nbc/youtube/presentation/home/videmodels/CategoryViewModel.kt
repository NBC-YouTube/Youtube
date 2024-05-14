package com.nbc.youtube.presentation.home.videmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nbc.youtube.data.repository.testRepo
import kotlinx.coroutines.launch

class CategoryViewModel(): ViewModel() {
    private val repository = testRepo()
    private val _categoryFromApi = MutableLiveData<List<String>>()
    val categoryFromApi : LiveData<List<String>> get() =_categoryFromApi
    fun loadCategories() {
        viewModelScope.launch {
            _categoryFromApi.value = repository.getCategories()
        }
    }
}