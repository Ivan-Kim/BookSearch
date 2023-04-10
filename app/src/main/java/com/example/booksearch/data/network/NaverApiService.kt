package com.example.booksearch.data.network

import retrofit2.http.GET
import retrofit2.http.Query

interface NaverApiService {
    @GET("/v1/search/book.json")
    suspend fun fetchBooks(
        @Query("query") query: String,
        @Query("display") display: Int = 10,
        @Query("start") start: Int = 1,
    ): BooksResponse
}
