package com.example.newsapplication.repository

import com.example.newsapplication.entities.News
import com.example.newsapplication.remote.NewsApi
import com.example.newsapplication.remote.NewsResponse
import com.example.newsapplication.utils.Resource
import com.example.newsapplication.utils.Status
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.Response
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

