package com.example.booksearch.data.di

import com.example.booksearch.data.network.BooksRepository
import com.example.booksearch.data.network.DefaultBooksRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepoModule {
    @Binds
    abstract fun providesRepo(impl: DefaultBooksRepository): BooksRepository
}