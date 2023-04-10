package com.example.booksearch.data.di

import com.example.booksearch.data.database.DefaultRecentQueryRepository
import com.example.booksearch.data.database.RecentQueryRepository
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
    abstract fun providesBooksRepo(impl: DefaultBooksRepository): BooksRepository

    @Binds
    abstract fun providesRecentRepo(impl: DefaultRecentQueryRepository): RecentQueryRepository

}
