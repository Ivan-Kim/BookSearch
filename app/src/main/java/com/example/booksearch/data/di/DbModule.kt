package com.example.booksearch.data.di

import android.content.Context
import androidx.room.Room
import com.example.booksearch.data.database.RecentQueryDatabase
import com.example.booksearch.data.database.SearchTimeConverter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context): RecentQueryDatabase =
        Room.databaseBuilder(context, RecentQueryDatabase::class.java, "recent_query_database")
            .addTypeConverter(SearchTimeConverter())
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun providesDao(db: RecentQueryDatabase) = db.recentQueryDao()

}
