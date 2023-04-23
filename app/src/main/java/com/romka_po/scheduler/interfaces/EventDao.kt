package com.romka_po.scheduler.interfaces

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.romka_po.scheduler.model.Event
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(event: Event)

    @Query("SELECT * FROM eventInfoTable")
    fun getEvents(): Flow<List<Event>>
    @Delete
    suspend fun delete(event: Event)
}