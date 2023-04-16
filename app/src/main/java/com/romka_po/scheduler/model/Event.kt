package com.romka_po.scheduler.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "eventInfoTable")
data class Event(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    val id:Int,

    @ColumnInfo
    val date_start:Long,
    @ColumnInfo
    val date_finish:Long,
    @ColumnInfo
    val name:String,
    @ColumnInfo
    val description:String?
)
