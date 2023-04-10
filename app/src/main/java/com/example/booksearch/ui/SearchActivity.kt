package com.example.booksearch.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.booksearch.R
import com.example.booksearch.ui.books.BooksFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, BooksFragment.newInstance())
                .commitNow()
        }
    }
}
