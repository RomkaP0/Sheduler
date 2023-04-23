package com.romka_po.scheduler.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.romka_po.scheduler.model.Event
import com.romka_po.scheduler.utils.TimestampConverter

@Composable
fun EventDialog(
    dialogState: MutableState<Boolean>,
    event: Event,
    onDrop: ((Event) -> Unit)?,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(1f), shape = RoundedCornerShape(8.dp), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(verticalArrangement = Arrangement.SpaceBetween) {
            event.apply {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primaryContainer)) {
                    Text(text = name, style = MaterialTheme.typography.headlineLarge, modifier = Modifier.padding(8.dp), maxLines = 1)

                }
                Text(text = "Start:  ${TimestampConverter.convertLongToDateTime(date_start)}", style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(8.dp))
                Text(text = "Finish: ${TimestampConverter.convertLongToDateTime(date_finish)}", style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(8.dp))
                description?.let { Text(text = "Description: $it", style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(8.dp)) }
            }
            Row(modifier = Modifier.padding(8.dp)) {
                OutlinedIconButton(onClick = { onDrop?.let { it(event) }; dialogState.value=false}) {
                    Icon(Icons.Default.Delete, contentDescription = "onDelete")
                }
//                OutlinedIconButton(onClick = { dropDialog(onDrop = onDrop, dialogState=dialogState)}) {
//                    Icon(Icons.Default.Edit, contentDescription = "onEdit")
//                }
                Button(onClick = {dialogState.value=false}){}
            }
        }
    }
}
