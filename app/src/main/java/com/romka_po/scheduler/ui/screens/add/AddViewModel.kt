package com.romka_po.scheduler.ui.screens.add

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.romka_po.scheduler.model.AppNavigator
import com.romka_po.scheduler.model.Destination
import com.romka_po.scheduler.model.Event
import com.romka_po.scheduler.repositories.EventRepository
import com.romka_po.scheduler.utils.AddEventStates
import com.romka_po.scheduler.utils.AddScreenEvent
import com.romka_po.scheduler.utils.TimestampConverter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
    private val appNavigator: AppNavigator,
    private val repository: EventRepository,
    savedStateHandle: SavedStateHandle

) : ViewModel() {
    private val _state = mutableStateOf(AddEventStates())
    val state: State<AddEventStates> = _state


    init {
        val timestamp:String = savedStateHandle.get<String>(Destination.AddEvent.date_key) ?: ""
        if (timestamp!=""){
            onEvent(AddScreenEvent.PutStartTime(timestamp.toLong()))
        }
    }
    fun onEvent(event: AddScreenEvent) {
        when (event) {
            is AddScreenEvent.PutLabel -> {
                _state.value = state.value.copy(
                    label = state.value.label.copy(
                        text = event.value
                    )
                )
            }
            is AddScreenEvent.PutStartTime -> {
                _state.value = state.value.copy(
                    start_time = state.value.start_time.copy(
                        text = TimestampConverter.convertLongToDateTime(event.value)
                    ),

                    finish_time = state.value.finish_time.copy(
                        text = TimestampConverter.convertLongToDateTime(event.value+3600000)
                    )
                )
                Log.d("ViewModel", event.value.toString())

            }
            is AddScreenEvent.PutFinishTime -> {
                _state.value = state.value.copy(
                    finish_time = state.value.finish_time.copy(
                        text = TimestampConverter.convertLongToDateTime(event.value)
                    )
                )
            }
            is AddScreenEvent.PutDesc -> {
                _state.value = state.value.copy(
                    desc = state.value.desc.copy(
                        text = event.value
                    )
                )
            }

        }
    }


    fun onNavigateToUsersButtonClicked() {
        appNavigator.tryNavigateBack()
    }


    fun insertEvent(event: Event) = viewModelScope.launch {
        repository.insertEvent(event)
    }

}