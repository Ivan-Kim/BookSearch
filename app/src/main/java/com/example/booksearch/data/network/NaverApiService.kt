package com.example.booksearch.data.network

import com.example.booksearch.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NaverApiService {
    @Headers(
        "X-Naver-Client-Id: ${BuildConfig.ClientId}",
        "X-Naver-Client-Secret: ${BuildConfig.ClientSecret}"
    )
    @GET("/v1/search/book.json")
    suspend fun fetchBooks(
        @Query("query") query: String,
        @Query("display") display: Int = 10,
        @Query("start") start: Int = 1,
    ): BooksResponse
}