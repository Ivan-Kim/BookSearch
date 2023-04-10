package com.example.booksearch.data.database

import javax.inject.Inject

class DefaultRecentQueryRepository @Inject constructor(
    private val recentQueryDao: RecentQueryDao
) : RecentQueryRepository {

    override suspend fun getAllRecentQuery(): List<RecentQueryEntity> = recentQueryDao.getAll()

    override suspend fun insertRecentQuery(query: RecentQueryEntity) {
        recentQueryDao.insert(query)
        val allRecentQuery = recentQueryDao.getAll()
        if (allRecentQuery.size > 10) deleteRecentQuery(allRecentQuery.last())
    }

    override suspend fun updateRecentQuery(query: RecentQueryEntity) = recentQueryDao.update(query)

    override suspend fun deleteRecentQuery(query: RecentQueryEntity) = recentQueryDao.delete(query)

}
