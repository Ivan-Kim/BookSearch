package com.example.booksearch.data.network

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.booksearch.data.di.BooksPagingSourceFactory
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultBooksRepository @Inject constructor(
    private val booksPagingSourceFactory: BooksPagingSourceFactory
) : BooksRepository {
    companion object {
        const val NETWORK_PAGE_SIZE = 10
    }

    override fun getBooksResultStream(query: String): Flow<PagingData<BookItem>> {
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = { booksPagingSourceFactory.create(query) }
        ).flow
    }
}