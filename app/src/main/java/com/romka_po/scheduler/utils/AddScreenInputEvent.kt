package com.romka_po.scheduler.utils

sealed class AddScreenInputEvent {
    data class EnteredLabel(val value: String) : AddScreenInputEvent()
    data class EnteredStartDateTime(val value: String) : AddScreenInputEvent()
    data class EnteredFinishDateTime(val value: String) : AddScreenInputEvent()
    data class EnteredDescription(val value: String) : AddScreenInputEvent()
    data class FocusChange(val focusFieldName: String) : AddScreenInputEvent()
}