package com.example.booksearch.ui.books

import com.example.booksearch.data.network.BookItem

data class BooksState(
    val query: String = "",
    val books: List<BookItem> = emptyList(),
)