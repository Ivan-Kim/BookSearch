package com.example.booksearch.ui.books

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.booksearch.data.network.BookItem
import com.example.booksearch.data.network.BooksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class BooksViewModel @Inject constructor(
    private val booksRepository: BooksRepository
) : ViewModel() {

    private val currQuery = MutableStateFlow("")

    val pagingDataFlow: Flow<PagingData<BookItem>> = currQuery
        .flatMapLatest { booksRepository.getBooksResultStream(it) }
        .cachedIn(viewModelScope)

    fun updateBooks(query: String) {
        currQuery.value = query
    }
}