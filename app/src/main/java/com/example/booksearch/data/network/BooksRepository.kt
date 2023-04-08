package com.example.booksearch.data.network

interface BooksRepository {
    suspend fun getBooks(query: String): List<BookItem>
}