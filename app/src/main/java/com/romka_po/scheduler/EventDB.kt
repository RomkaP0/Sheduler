package com.romka_po.scheduler

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.romka_po.scheduler.interfaces.EventDBDao
import com.romka_po.scheduler.model.Event

@Database(entities = [Event::class], version = 1, exportSchema = false)
abstract class EventDB : RoomDatabase() {
    abstract fun getEventDBDao(): EventDBDao

    companion object {
        @Volatile
        private var instance: EventDB? = null
        private val LOCK = Any()


        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                EventDB::class.java,
                "event_info.db")
                .build()
    }
}