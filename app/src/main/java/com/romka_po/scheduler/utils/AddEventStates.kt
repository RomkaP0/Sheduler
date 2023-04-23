package com.romka_po.scheduler.utils

data class AddEventStates(
    val label : InputState = InputState(type = InputType.LABEL),
    val start_time : InputState = InputState(type = InputType.START_TIME),
    val finish_time : InputState = InputState(type = InputType.FINISH_TIME),
    val desc : InputState = InputState(type = InputType.DESC),

    )