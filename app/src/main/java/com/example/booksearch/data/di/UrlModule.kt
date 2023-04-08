package com.example.booksearch.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UrlModule {
    @Provides
    fun providesBaseUrl(): String = "https://openapi.naver.com"
}