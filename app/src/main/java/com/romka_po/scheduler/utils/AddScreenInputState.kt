package com.romka_po.scheduler.utils

data class LoginFormState(
    val text : InputState = InputState(type = InputType.TEXT),
    val startdatetime: InputState = InputState(type = InputType.STARTDATETIME),
    val finishdatetime: InputState = InputState(type = InputType.FINISHDATETIME),
    val description: InputState = InputState(type = InputType.DESCRIPTION),
    val formValid: Boolean
)