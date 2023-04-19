package com.romka_po.scheduler.repositories

import com.romka_po.scheduler.interfaces.EventDao
import com.romka_po.scheduler.model.Event
import javax.inject.Inject

class EventRepository @Inject constructor (
    private val eventDao: EventDao
){
    fun getDayEvents(date_start:Long, date_finish:Long) = eventDao.getDayEvents(date_start,date_finish)

    suspend fun insertEvent(event: Event)  =eventDao.insert(event)

    suspend fun deleteEvent(event: Event) = eventDao.delete(event)
}