package com.example.booksearch.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [RecentQueryEntity::class], version = 1, exportSchema = false)
@TypeConverters(SearchTimeConverter::class)
abstract class RecentQueryDatabase : RoomDatabase() {
    abstract fun recentQueryDao(): RecentQueryDao
}
