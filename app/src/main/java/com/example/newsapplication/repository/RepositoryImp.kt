package com.example.newsapplication.repository

import com.example.newsapplication.remote.NewsApi
import com.example.newsapplication.utils.Resource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImp
@Inject
constructor(private val newsApi: NewsApi) :  NewsRepository {

    override suspend fun getNews(country: String,apiKey: String)=
         flow {
            emit(Resource.success(newsApi.getNews(country, apiKey).body()))
        }
    }

