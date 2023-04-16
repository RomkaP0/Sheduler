package com.romka_po.scheduler.ui.screens.add

import android.text.TextUtils
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.romka_po.scheduler.model.AppNavigator
import com.romka_po.scheduler.utils.AddScreenInputEvent
import com.romka_po.scheduler.utils.InputType
import com.romka_po.scheduler.utils.LoginFormState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(private val appNavigator: AppNavigator) : ViewModel() {
    private val _state = mutableStateOf(LoginFormState(formValid = true))
    val state: State<LoginFormState> = _state
    fun onNavigateToUsersButtonClicked() {
        appNavigator.tryNavigateBack()
    }


    fun createEvent(event: AddScreenInputEvent) {
        onEvent(event)
    }

    private fun onEvent(event: AddScreenInputEvent) {
        when (event) {
            is AddScreenInputEvent.EnteredLabel -> {
                _state.value = state.value.copy(
                    text = state.value.text.copy(
                        text = event.value
                    )
                )
            }
            is AddScreenInputEvent.EnteredStartDateTime -> {
                _state.value = state.value.copy(
                    startdatetime = state.value.startdatetime.copy(
                        text = event.value
                    )
                )
            }
            is AddScreenInputEvent.EnteredFinishDateTime -> {
                _state.value = state.value.copy(
                    finishdatetime = state.value.finishdatetime.copy(
                        text = event.value
                    )
                )
            }
            is AddScreenInputEvent.EnteredDescription -> {
                _state.value = state.value.copy(
                    description = state.value.description.copy(
                        text = event.value
                    )
                )
            }
            is AddScreenInputEvent.FocusChange -> {
                when (event.focusFieldName) {
                    "text" -> {
                        val textValid = validateInput(state.value.text.text, InputType.TEXT)
                        _state.value = state.value.copy(
                            text = state.value.text.copy(
                                isValid = textValid),
                            formValid = textValid
                        )
                    }
//                    "startdatetime" -> {
//                        val passwordValid = validateInput(
//                            state.value.password.text,
//                            InputType.PASSWORD
//                        )
//                        _state.value = state.value.copy(
//                            password = state.value.password.copy(
//                                isValid = passwordValid),
//                            formValid = passwordValid
//                        )
//                    }
                }
            }
        }
    }

    private fun validateInput(inputValue: String, inputType: InputType): Boolean {
        when (inputType) {
            InputType.TEXT -> {
                return !TextUtils.isEmpty(inputValue)
            }
//            InputType. -> {
//                return !TextUtils.isEmpty(inputValue) && inputValue.length > 5
//
//            }


            else -> {return true}
        }

    }
}