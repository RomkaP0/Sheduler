package com.romka_po.scheduler.repositories

import com.romka_po.scheduler.interfaces.EventDao
import com.romka_po.scheduler.model.Event
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EventRepository @Inject constructor (
    private val eventDao: EventDao
){
    fun getDayEvents(date_start:Long, date_finish:Long): Flow<List<Event>> = eventDao.getDayEvents(date_start,date_finish)

    fun getEvent(): Flow<List<Event>> =eventDao.getEvents()
    suspend fun insertEvent(event: Event)  =eventDao.insert(event)

    suspend fun deleteEvent(event: Event) = eventDao.delete(event)
}