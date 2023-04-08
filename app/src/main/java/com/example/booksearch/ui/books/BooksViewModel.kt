package com.example.booksearch.ui.books

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksearch.data.network.BooksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(
    private val booksRepository: BooksRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(BooksState())
    val uiState: StateFlow<BooksState> = _uiState.asStateFlow()

    fun updateBooks(query: String) {
        viewModelScope.launch {
            val books = booksRepository.getBooks(query)
            _uiState.value = BooksState(query, books)
        }
    }
}