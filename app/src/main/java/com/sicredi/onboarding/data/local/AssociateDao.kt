package com.sicredi.onboarding.data.local


import androidx.room.*
import com.sicredi.onboarding.data.model.Associate
import kotlinx.coroutines.flow.Flow


@Dao
interface AssociateDao {
    @Query("SELECT * FROM associates ORDER BY createdAt DESC")
    fun observeAll(): Flow<List<Associate>>


    @Query("SELECT * FROM associates WHERE id = :id")
    suspend fun getById(id: Long): Associate?


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: Associate): Long


    @Update
    suspend fun update(entity: Associate)


    @Delete
    suspend fun delete(entity: Associate)
}