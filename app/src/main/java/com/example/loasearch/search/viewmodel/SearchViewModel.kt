package com.example.loasearch.search.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchViewModel: ViewModel() {
    private val _newsData = MutableLiveData<String>()
    val newsData: LiveData<String> get() = _newsData
}