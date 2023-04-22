package com.romka_po.scheduler.ui.screens.show

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.romka_po.scheduler.model.AppNavigator
import com.romka_po.scheduler.model.Destination
import com.romka_po.scheduler.model.Event
import com.romka_po.scheduler.repositories.EventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ShowViewModel @Inject constructor(private val appNavigator: AppNavigator, private val repository: EventRepository):ViewModel(){
    private val _listEvents = mutableStateOf(emptyList<Event>())
    val listEvents: State<List<Event>> = _listEvents

    val noteListFlow: Flow<List<Event>> = repository.getEvent()




    fun onNavigateToUsersButtonClicked() {
        appNavigator.tryNavigateTo(Destination.AddEvent("26.10.2002"))
    }
//    fun getEvents(date_start:Long, date_finish:Long) = viewModelScope.launch {
//        getNotesJob?.cancel()
//        getNotesJob = repository.getDayEvents(0, 1000000000000000000.toLong())
//            .onEach { _ ->
//                _listEvents.value = listEvents.value
//            }
//            .launchIn(viewModelScope)
//        Log.d("StateInstanceVM", _listEvents.value.size.toString())
//    }
//        fun getEvents() = viewModelScope.launch {
//        getNotesJob?.cancel()
//        getNotesJob = repository.getEvent()
//            .onEach { _ ->
//                _listEvents.value = listEvents.value
//            }
//            .launchIn(viewModelScope)
//        Log.d("StateInstanceVM", _listEvents.value.size.toString())
//    }


}