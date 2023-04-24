@file:OptIn(ExperimentalMaterial3Api::class)

package com.romka_po.scheduler.ui.common

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import java.util.TimeZone

@Composable
fun TimeDateDialog(
    dialogState: MutableState<Boolean>,
    label: String,
    onValueChange: (Long) -> Unit,
    timePickerState: TimePickerState,
    datePickerState: DatePickerState

    ) {

    Card(
        modifier = Modifier
            .fillMaxWidth(1f),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        val context = LocalContext.current

        Column(
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primaryContainer)
            ) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.padding(8.dp)
                )
            }
            Spacer(modifier = Modifier.height(40.dp))
            TimeInput(state = timePickerState)
            DatePicker(
                state = datePickerState,
                showModeToggle = false,
                headline = null,
                title = null
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                OutlinedButton(onClick = { dialogState.value = false }) {
                    Text(text = "Cancel")
                }
                Button(onClick = {
                    if (datePickerState.selectedDateMillis!=null) {
                        val c =
                            datePickerState.selectedDateMillis!! + ((timePickerState.hour * 60 + timePickerState.minute) * 60000 - TimeZone.getDefault().rawOffset)
                        Log.d("Click", c.toString())

                        onValueChange(c)
                        dialogState.value = false
                    }
                    else{
                        Toast.makeText(context, "Please, put Date", Toast.LENGTH_SHORT).show()
                    }
                }) {
                    Text(text = "Submit")
                }
            }
            Spacer(modifier = Modifier.height(10.dp))

        }
    }
}