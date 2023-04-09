package com.example.booksearch.data.network

import com.google.gson.annotations.SerializedName

data class BooksResponse(
    @SerializedName("items")
    val items: List<BookItem>,
)

data class BookItem(
    @SerializedName("title")
    val title: String,
    @SerializedName("link")
    val link: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("author")
    val author: String,
    @SerializedName("publisher")
    val publisher: String,
    @SerializedName("discount")
    val discount: Int,
)
