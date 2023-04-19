package com.romka_po.scheduler

import androidx.room.Database
import androidx.room.RoomDatabase
import com.romka_po.scheduler.interfaces.EventDao
import com.romka_po.scheduler.model.Event

@Database(entities = [Event::class], version = 1, exportSchema = false)
abstract class EventDB : RoomDatabase() {
    abstract fun getEventDao(): EventDao


}