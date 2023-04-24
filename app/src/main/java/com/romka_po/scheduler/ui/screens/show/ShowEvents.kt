@file:OptIn(ExperimentalMaterial3Api::class)

package com.romka_po.scheduler.ui.screens.show

import android.util.Log
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Today
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.romka_po.scheduler.model.Event
import com.romka_po.scheduler.ui.common.TimeLineView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.TimeZone

@Composable
fun ShowEventsScreen(
    showViewModel: ShowViewModel = hiltViewModel()

) {
    val datePickerState = rememberDatePickerState(System.currentTimeMillis()+ TimeZone.getDefault().rawOffset)
    Log.d("CalendarInstance", datePickerState.selectedDateMillis.toString())
    val noteListState = showViewModel.noteListFlow.collectAsState(initial = listOf())
    val composableScope = rememberCoroutineScope()




    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomAppBar(
                actions = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Filled.Search, contentDescription = "geg")
                    }
                    IconButton(onClick = {
                        datePickerState.setSelection( System.currentTimeMillis()+ TimeZone.getDefault().rawOffset)
                    }) {
                        Icon(Icons.Filled.Today, contentDescription = "geg")
                    }
                },
                floatingActionButton = {
                    FloatingActionButton(onClick = {
                        showViewModel.onNavigateToUsersButtonClicked(
                            datePickerState.selectedDateMillis!!-TimeZone.getDefault().rawOffset
                        )
                    }) {
                        Icon(Icons.Filled.Add, contentDescription = "Add Event")
                    }
                },

                )
        }
    ) {
        BoxWithConstraints(
            modifier = Modifier
                .padding(it)
        ) {
            if (maxWidth>maxHeight){
                Row {
                    ShowContent(datePickerState,noteListState, composableScope, showViewModel, true)
                }
            }
            else{
                Column {
                    ShowContent(datePickerState,noteListState, composableScope, showViewModel, false)
                }
            }
        }
    }

}

@Composable
fun ShowContent(datePickerState:DatePickerState, noteListState: State<List<Event>>, composableScope:CoroutineScope, showViewModel: ShowViewModel, isLand:Boolean){
    MaterialTheme.colorScheme.apply {
        DatePicker(
            modifier = if (isLand) Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth(0.5f) else Modifier,
            state = datePickerState,
            showModeToggle = false,
            headline = null,
            title = null,
            colors = DatePickerDefaults.colors(
                todayDateBorderColor = primaryContainer,
                selectedDayContainerColor = primary
            )
        )
    }

    TimeLineView(
        list = noteListState.value,
        startTime = datePickerState.selectedDateMillis!!,
        dropEvent = {
            composableScope.launch {
                showViewModel.dropEvent(event = it)
            }
        },
        dialogState = showViewModel.dialogState,
        eventState = showViewModel.currentEventState
    )
}

