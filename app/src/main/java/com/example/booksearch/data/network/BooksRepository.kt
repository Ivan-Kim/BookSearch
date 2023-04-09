package com.example.booksearch.data.network

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface BooksRepository {
    fun getBooksResultStream(query: String): Flow<PagingData<BookItem>>
}