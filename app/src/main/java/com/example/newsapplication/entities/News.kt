package com.example.newsapplication.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class News(
    val author: String? = null,
    val content: String? = null,
    val description: String? = null,
    val publishedAt: String? = null,
    val source: Source? = null,
    val title: String? = null,
    val url: String? = null,
    val urlToImage: String? = null,
    val articles: List<News>
): Parcelable

@Parcelize
data class Source(
    val id: String?,
    val name: String
): Parcelable
