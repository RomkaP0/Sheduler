package com.romka_po.scheduler

import com.romka_po.scheduler.utils.TimestampConverter
import org.junit.Assert
import org.junit.Test

class TimestampTest {

    @Test
    fun toTimestamp() {
        Assert.assertEquals(1035612000000, TimestampConverter.convertStringToTimestamp("26.10.2002 13:00"))
    }
    @Test
    fun toDate(){
        Assert.assertEquals("26.10.2002 13:00", TimestampConverter.convertLongToDateTime(1035612000000))
    }
}