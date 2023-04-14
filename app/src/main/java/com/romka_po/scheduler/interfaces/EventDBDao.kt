package com.romka_po.scheduler.interfaces

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Delete
import com.romka_po.scheduler.model.Event

@Dao
interface EventDBDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(event: Event)

    @Query("SELECT * FROM eventInfoTable WHERE :date_start<=date_finish and :date_finish>=date_start")
    suspend fun getDayEvents(date_start:Long, date_finish:Long): LiveData<List<Event>>

    @Delete
    suspend fun delete(event: Event)
}