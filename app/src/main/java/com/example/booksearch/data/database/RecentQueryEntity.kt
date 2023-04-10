package com.example.booksearch.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "recent_query")
data class RecentQueryEntity(
    @PrimaryKey val query: String,
    val searchTime: LocalDateTime,
)
