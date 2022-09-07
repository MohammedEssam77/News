package com.example.newsapplication.repository

import com.example.newsapplication.entities.News
import com.example.newsapplication.utils.Resource
import kotlinx.coroutines.flow.Flow


interface NewsRepository {
  suspend fun getNews(country: String,apiKey: String): Flow<Resource<News>>
}