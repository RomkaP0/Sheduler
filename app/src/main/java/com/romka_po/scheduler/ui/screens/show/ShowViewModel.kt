package com.romka_po.scheduler.ui.screens.show

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.romka_po.scheduler.model.AppNavigator
import com.romka_po.scheduler.model.Destination
import com.romka_po.scheduler.model.Event
import com.romka_po.scheduler.repositories.EventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowViewModel @Inject constructor(private val appNavigator: AppNavigator, private val repository: EventRepository):ViewModel(){
    private val _listEvents = mutableStateOf(emptyList<Event>())
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    val noteListFlow: Flow<List<Event>> = repository.getEvent().distinctUntilChanged()
    

    val dialogState:MutableState<Boolean> = mutableStateOf(false)
    val currentEventState: MutableState<Event> = mutableStateOf(Event( 0, 0, "Something Wrong",null,-1))


    fun onNavigateToUsersButtonClicked(timestamp:Long) {
        appNavigator.tryNavigateTo(Destination.AddEvent(timestamp.toString()))
    }

    suspend fun dropEvent(event: Event){
        coroutineScope.launch {
            repository.deleteEvent(event)
        }
    }



}