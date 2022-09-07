package com.example.newsapplication.remote


import com.example.newsapplication.entities.News
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsApi {
    @GET("top-headlines")
    suspend fun getNews(@Query("country") country: String, @Query("apiKey") apiKey: String?)
    :Response<News>
}