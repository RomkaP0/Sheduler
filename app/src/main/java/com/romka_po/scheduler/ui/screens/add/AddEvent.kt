@file:OptIn(ExperimentalMaterial3Api::class)

package com.romka_po.scheduler.ui.screens.add

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

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
        Column(modifier = Modifier.padding(it)) {
            Box(modifier = Modifier.fillMaxWidth()){
                Icon(Icons.Filled.Menu, contentDescription = "Label")
//                TextField(value = ,)
            }
        }
    }
}

fun formingEvent(){

}