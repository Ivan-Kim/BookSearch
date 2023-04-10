package com.example.booksearch.data.database

import androidx.room.*

@Dao
interface RecentQueryDao {

    @Query("SELECT * FROM recent_query ORDER BY searchTime DESC")
    suspend fun getAll(): List<RecentQueryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(query: RecentQueryEntity)

    @Update
    suspend fun update(query: RecentQueryEntity)

    @Delete
    suspend fun delete(query: RecentQueryEntity)

}
