package com.example.booksearch.data.di

import com.example.booksearch.data.network.BooksPagingSource
import dagger.assisted.AssistedFactory

@AssistedFactory
interface BooksPagingSourceFactory {
    fun create(query: String): BooksPagingSource
}