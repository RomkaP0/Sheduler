@file:OptIn(ExperimentalMaterial3Api::class)

package com.romka_po.scheduler.ui.screens.show

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.romka_po.scheduler.model.AppNavigatorImpl
import com.romka_po.scheduler.ui.common.TimeLineView

@Composable
fun ShowEventsScreen(
    showViewModel: ShowViewModel= hiltViewModel()

) {

    Scaffold(

        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomAppBar(
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Filled.Search, contentDescription = "geg")
                    }
                    IconButton(onClick = { /*TODO*/ }) {
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
        val datePickerState = rememberDatePickerState()
        Column(modifier = Modifier
            .padding(it)) {
            DatePicker(state = datePickerState, showModeToggle = false, headline = null, title = null, colors = DatePickerDefaults.colors())
            TimeLineView(list = com.romka_po.scheduler.ui.common.check())
        }
    }

}




@Preview()
@Composable
fun DefaultPreview(){
    ShowEventsScreen(ShowViewModel(AppNavigatorImpl()))
}

