package com.example.booksearch.data.database

interface RecentQueryRepository {

    suspend fun getAllRecentQuery(): List<RecentQueryEntity>

    suspend fun updateRecentQuery(query: RecentQueryEntity)

    suspend fun insertRecentQuery(query: RecentQueryEntity)

    suspend fun deleteRecentQuery(query: RecentQueryEntity)

}
