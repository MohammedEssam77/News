package com.example.newsapplication.repository

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsHandler
@Inject constructor(private val newsRepository: NewsRepository) {
   suspend fun getNews(country: String,apiKey: String) =newsRepository.getNews(country, apiKey)
}