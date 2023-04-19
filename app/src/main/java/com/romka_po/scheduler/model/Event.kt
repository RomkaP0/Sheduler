package com.romka_po.scheduler.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "eventInfoTable")
data class Event(
    @PrimaryKey(autoGenerate = true)
    val id: Long =0,

    val date_start:Long,
    val date_finish:Long,
    val name:String,
    val description:String? = null
)
