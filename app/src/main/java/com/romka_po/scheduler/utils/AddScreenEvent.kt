package com.romka_po.scheduler.utils

sealed class AddScreenEvent {
    data class PutLabel(val value: String) : AddScreenEvent()
    data class PutStartTime(val value: Long) : AddScreenEvent()
    data class PutFinishTime(val value: Long) : AddScreenEvent()
    data class PutDesc(val value: String) : AddScreenEvent()
}