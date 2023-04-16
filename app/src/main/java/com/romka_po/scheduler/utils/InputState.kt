package com.romka_po.scheduler.utils

enum class InputType {
    TEXT,
    STARTDATETIME,
    FINISHDATETIME,
    DESCRIPTION
}
data class InputState(
    val text: String = "",
    val isValid: Boolean = true,
    val type: InputType,
)