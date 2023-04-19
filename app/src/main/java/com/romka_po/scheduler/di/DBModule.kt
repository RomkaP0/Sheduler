package com.romka_po.scheduler.di

import android.content.Context
import androidx.room.Room
import com.romka_po.scheduler.EventDB
import com.romka_po.scheduler.interfaces.EventDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DBModule {

    @Provides
    fun provideEventDatabase(@ApplicationContext context: Context):EventDB{
        return Room.databaseBuilder(context, EventDB::class.java, "event.db").build()
    }

    @Provides
    fun provideEventDao(eventDB: EventDB): EventDao{
        return eventDB.getEventDao()
    }
}