@file:OptIn(ExperimentalMaterial3Api::class)

package com.romka_po.scheduler.ui.screens.show

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.himanshoe.kalendar.Kalendar
import com.himanshoe.kalendar.model.KalendarType
import com.romka_po.scheduler.model.AppNavigatorImpl
import com.romka_po.scheduler.ui.common.EventHolder
import com.romka_po.scheduler.ui.common.TimeLine

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
        Column(modifier = Modifier.padding(it).padding(horizontal = 10.dp)) {
            Kalendar(kalendarType = KalendarType.Oceanic(true))
            val scrollableState = rememberScrollState()

            TimeLine(
                hoursLabel = { HoursHeader(hours = (0..23)) },
                rowCount = 2,
                modifier = Modifier.verticalScroll(scrollableState,true),
                eventBox = {index ->
                    val data  = com.romka_po.scheduler.ui.common.check()[index]
                    EventHolder(data, modifier = Modifier.fillMaxWidth(0.8F)
                        .timeLineBar(data.date_start, data.date_finish)
                        .background(
                            Color.Cyan, shape = FloatingActionButtonDefaults.shape
                        )
                    )
                })        }
    }

}

@Composable
private fun HoursHeader(hours: IntRange) {
    Column(
        Modifier
            .padding(bottom = 16.dp)
    ) {
        hours.forEach {
            Text(
                text = if (it<10) "0$it:00" else("$it:00"),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .width(50.dp)
                    .height(30.dp)
//                    .padding(vertical = 4.dp)
            )
        }
    }
}


@Preview()
@Composable
fun DefaultPreview(){
    ShowEventsScreen(ShowViewModel(AppNavigatorImpl()))
}