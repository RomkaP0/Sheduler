package com.romka_po.scheduler.utils

import java.sql.Timestamp
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date

object TimestampConverter {
    fun convertLongToDateTime(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat("dd.MM.yyyy HH:mm")
        return format.format(date)
    }

    fun convertLongToTime(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat("HH:mm")
        return format.format(date)
    }


    fun currentDayTimeToLong(): Long {
        return System.currentTimeMillis()
    }

    fun convertStringToTimestamp(something: String): Long {
        var dateFormat: SimpleDateFormat? = null
        if (something.contains(".")) {
            dateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm")
        }
        if (something.contains(",")) {
            dateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm")
        }
        var timestamp: Timestamp? = null
        val parsedDate: Date
        try {
            parsedDate = dateFormat!!.parse(something)
            timestamp = Timestamp(parsedDate.time)
        } catch (e: ParseException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }
        return timestamp!!.time
    }
}