@file:OptIn(ExperimentalMaterial3Api::class)

package com.romka_po.scheduler.ui.screens.add

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Event
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.romka_po.scheduler.model.Event
import com.romka_po.scheduler.ui.common.InsertDateTimeField
import com.romka_po.scheduler.utils.AddEventStates
import com.romka_po.scheduler.utils.AddScreenEvent
import com.romka_po.scheduler.utils.TimestampConverter
import kotlinx.coroutines.launch

@Composable
fun AddEvent(
    addViewModel: AddViewModel = hiltViewModel()

) {
    val context = LocalContext.current
    val coroutinesScope = rememberCoroutineScope()
    val inputState = addViewModel.state.value


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomAppBar(
                actions = {
                    IconButton(onClick = { addViewModel.onNavigateToUsersButtonClicked() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "geg")
                    }

                },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = {
                            inputState.apply {
                                if (label.text != "") {
                                    coroutinesScope.launch {
                                        addViewModel.insertEvent(
                                            Event(
                                                date_start = TimestampConverter.convertStringToTimestamp(
                                                    start_time.text
                                                ),
                                                date_finish = TimestampConverter.convertStringToTimestamp(
                                                    finish_time.text
                                                ),
                                                name = label.text,
                                                description = desc.text
                                            )
                                        )
                                    }
                                    addViewModel.onNavigateToUsersButtonClicked()

                                } else {
                                    Toast.makeText(context, "Put Label", Toast.LENGTH_SHORT).show()
                                }
                            }
                        },
                    ) {
                        Icon(Icons.Filled.Done, contentDescription = "Add Event")
                    }
                },

                )
        }
    ) {
        BoxWithConstraints(
            modifier = Modifier
                .padding(it)
//                .padding(20.dp)
                .fillMaxSize(),
        ) {
            if (maxHeight>maxWidth){
                Column (modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceEvenly, horizontalAlignment = Alignment.CenterHorizontally){
                    AddScreenContent(inputState = inputState, addViewModel = addViewModel)
                    OutlinedTextField(
                        value = inputState.desc.text,
                        onValueChange = { addViewModel.onEvent(AddScreenEvent.PutDesc(it)) },
                        modifier = Modifier.heightIn(min = 200.dp),
                        label = { Text("description") },
                    )
                }
            }
            else{
                Row (modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceEvenly){
                    Column (verticalArrangement = Arrangement.SpaceBetween){
                        AddScreenContent(inputState = inputState, addViewModel = addViewModel)

                    }
                    OutlinedTextField(
                        value = inputState.desc.text,
                        onValueChange = { addViewModel.onEvent(AddScreenEvent.PutDesc(it)) },
                        modifier = Modifier.heightIn(min = 200.dp),
                        label = { Text("description") },
                    )
                }
            }

        }
    }
}

@Composable
fun AddScreenContent(inputState:AddEventStates, addViewModel: AddViewModel) {


    OutlinedTextField(
        value = inputState.label.text,
        onValueChange = { addViewModel.onEvent(AddScreenEvent.PutLabel(it)) },
        label = { Text("Label") },
        singleLine = true,
    )
    InsertDateTimeField(

        value = inputState.start_time.text,
        label = "Start",
        trailingIcon = {
            Icon(Icons.Default.Event, "startTime")
        },
        provide = { addViewModel.onEvent(AddScreenEvent.PutStartTime(it)) }
    )
    InsertDateTimeField(
        value = inputState.finish_time.text,
        label = "Finish",
        trailingIcon = {
            Icon(Icons.Default.Event, "finishTime")
        },
        provide = { addViewModel.onEvent(AddScreenEvent.PutFinishTime(it)) }

    )

}


