@file:OptIn(ExperimentalMaterial3Api::class)

package com.romka_po.scheduler.ui.screens.add

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.romka_po.scheduler.model.AppNavigatorImpl
import com.romka_po.scheduler.ui.common.CustomInputField
import com.romka_po.scheduler.utils.AddScreenInputEvent

@Composable
fun AddEvent(
    addViewModel: AddViewModel = hiltViewModel()

){
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
                    FloatingActionButton(onClick = {
                    }) {
                        Icon(Icons.Filled.Done, contentDescription = "Add Event")
                    }
                },

                )
        }
    ) {
        Column (
            modifier = Modifier
                .padding(it)
                .padding(20.dp)
                .fillMaxSize() ,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            val screenState = addViewModel.state.value
            CustomInputField(
                value = screenState.text.text,
                placeholder = "text",
                onFocusChange = {addViewModel.createEvent(AddScreenInputEvent.FocusChange("text"))},
                onValueChange = {
                    addViewModel.createEvent(AddScreenInputEvent.EnteredLabel(it))
                },
                singleLine = true)
            Spacer(modifier = Modifier.size(20.dp))
            CustomInputField(
                value = screenState.text.text,
                placeholder = "startdatetime",
                onFocusChange = {addViewModel.createEvent(AddScreenInputEvent.FocusChange("startdatetime"))},
                onValueChange = {
                    addViewModel.createEvent(AddScreenInputEvent.EnteredStartDateTime(it))
                },
                readOnly = true
            )
            Spacer(modifier = Modifier.size(20.dp))
            CustomInputField(
                value = screenState.text.text,
                placeholder = "finishdatetime",
                onFocusChange = {addViewModel.createEvent(AddScreenInputEvent.FocusChange("finishdatetime"))},
                onValueChange = {
                    addViewModel.createEvent(AddScreenInputEvent.EnteredFinishDateTime(it))
                },
                readOnly = true
            )
            Spacer(modifier = Modifier.size(20.dp))
            CustomInputField(
                value = screenState.text.text,
                placeholder = "text",
                onFocusChange = {addViewModel.createEvent(AddScreenInputEvent.FocusChange("text"))},
                onValueChange = {
                    addViewModel.createEvent(AddScreenInputEvent.EnteredLabel(it))
                })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun testAdd(){
    AddEvent(AddViewModel(AppNavigatorImpl()))
}