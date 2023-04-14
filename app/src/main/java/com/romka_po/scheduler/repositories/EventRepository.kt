package com.romka_po.scheduler.repositories

import com.romka_po.scheduler.EventDB
import com.romka_po.scheduler.model.Event

class EventRepository(
    val db: EventDB
){
    suspend fun getDayEvents(date_start:Long, date_finish:Long) = db.getEventDBDao().getDayEvents(date_start,date_finish)

    suspend fun insertEvent(event: Event)  =db.getEventDBDao().insert(event)

    suspend fun deleteEvent(event: Event) = db.getEventDBDao().delete(event)
}