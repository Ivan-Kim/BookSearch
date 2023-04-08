package com.example.booksearch.data.network

import javax.inject.Inject

class DefaultBooksRepository @Inject constructor(
    private val apiService: ApiService
) : BooksRepository {
    override suspend fun getBooks(query: String): List<BookItem>  {
        val response = apiService.fetchBooks(query)
        return response.items
    }
}