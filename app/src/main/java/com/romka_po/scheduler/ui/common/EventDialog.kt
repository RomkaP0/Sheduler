package com.romka_po.scheduler.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.romka_po.scheduler.model.Event

@Composable
fun EventDialog(
    dialogState: MutableState<Boolean>,
    event: MutableState<Event>
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(1f), shape = RoundedCornerShape(8.dp)
    ) {
        Column() {
            event.value.apply {
                Text(text = name)
                Text(text = date_start.toString())
                Text(text = date_finish.toString())
                description?.let { Text(text = it) }
            }
        }
    }
}