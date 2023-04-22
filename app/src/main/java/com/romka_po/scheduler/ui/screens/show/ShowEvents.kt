@file:OptIn(ExperimentalMaterial3Api::class)

package com.romka_po.scheduler.ui.screens.show

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.romka_po.scheduler.ui.common.TimeLineView

@Composable
fun ShowEventsScreen(
    showViewModel: ShowViewModel= hiltViewModel()

) {
    val datePickerState = rememberDatePickerState()
    Log.d("CalendarInstance", datePickerState.selectedDateMillis.toString())
//    datePickerState.selectedDateMillis?.let { showViewModel.getEvents(it, it+ 86400000) }
//    val customerList = showViewModel.fetchAllCustomer().observeAsState(arrayListOf())
    val noteListState = showViewModel.noteListFlow.collectAsState(initial = listOf())




    Scaffold(

        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomAppBar(
                actions = {
                    IconButton(onClick = {  }) {
                        Icon(Icons.Filled.Search, contentDescription = "geg")
                    }
                    IconButton(onClick = { Log.d("SizeInstance", showViewModel.listEvents.value.size.toString())}) {
                        Icon(Icons.Filled.Delete, contentDescription = "geg")
                    }
                    },
                floatingActionButton = {
                    FloatingActionButton(onClick = { showViewModel.onNavigateToUsersButtonClicked() }) {
                        Icon(Icons.Filled.Add, contentDescription = "Add Event")
                    }
                },

                )
        }
    ) {
        LazyColumn(){
            items(noteListState.value){it->
                Text(text = it.name)
            }
        }
        Column(modifier = Modifier
            .padding(it)) {
            MaterialTheme.colorScheme.apply {
                DatePicker(
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
            TimeLineView(list = noteListState.value)

        }
    }


}




//@Preview()
//@Composable
//fun DefaultPreview(){
//    ShowEventsScreen(ShowViewModel(AppNavigatorImpl()))
//}

