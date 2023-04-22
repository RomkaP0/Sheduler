package com.romka_po.scheduler.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.romka_po.scheduler.model.Event

@Composable
fun EventDialog(
    dialogState: MutableState<Boolean>,
    event: MutableState<Event>,
    onDrop: (() -> Unit)?,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(1f), shape = RoundedCornerShape(8.dp),
    ) {
        Column(verticalArrangement = Arrangement.SpaceBetween) {
            event.value.apply {
                Text(text = name, style = MaterialTheme.typography.headlineLarge, modifier = Modifier.padding(8.dp))
                Text(text = date_start.toString(), style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(8.dp))
                Text(text = date_finish.toString(), style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(8.dp))
                description?.let { Text(text = it, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(8.dp)) }
            }
            Row {
                OutlinedButton(
                    onClick = { dropDialog(onDrop = onDrop, dialogState=dialogState) },
                ){}
                Button(onClick = {dialogState.value=false}){}
            }
        }
    }
}
fun dropDialog(onDrop: (() -> Unit)?, dialogState: MutableState<Boolean>){
    onDrop?.let { it() }
    dialogState.value=false
}