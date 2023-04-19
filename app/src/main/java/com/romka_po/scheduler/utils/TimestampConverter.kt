package com.romka_po.scheduler.utils

import java.text.SimpleDateFormat
import java.util.Date

object TimestampConverter {
    fun convertLongToTime(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat("HH:mm")
        return format.format(date)
    }

    fun currentTimeToLong(): Long {
        return System.currentTimeMillis()
    }

    fun convertDateToLong(date: String): Long {
        val df = SimpleDateFormat("HH:mm")
        return df.parse(date).time
    }
}