package com.example.newsapplication.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapplication.entities.News
import com.example.newsapplication.remote.NewsResponse
import com.example.newsapplication.repository.NewsHandler
import com.example.newsapplication.utils.NetworkHelper
import com.example.newsapplication.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel
@Inject
constructor(private val newsHandler: NewsHandler,
            private val networkHelper: NetworkHelper) : ViewModel() {
    private val _newsLiveData = MutableLiveData<Resource<News>>()
    val data: LiveData<Resource<News>>
        get() = _newsLiveData

    fun getNews(country: String,apiKey: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _newsLiveData.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                newsHandler.getNews(country, apiKey).collect {
                    _newsLiveData.postValue(it)
                }
            }
        }
    }
}
