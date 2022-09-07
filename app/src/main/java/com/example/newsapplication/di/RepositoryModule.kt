package com.example.newsapplication.di

import com.example.newsapplication.repository.NewsRepository
import com.example.newsapplication.repository.RepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideRepository(repositoryImp: RepositoryImp):NewsRepository
}