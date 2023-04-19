package com.romka_po.scheduler.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.romka_po.scheduler.model.Event
import com.romka_po.scheduler.utils.TimestampConverter

@Composable
fun EventHolder(
    event: Event,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.padding(start = 4.dp)
    ) {
        Text(text = event.name, style = MaterialTheme.typography.bodyMedium)
        TimestampConverter.apply {
            Text(text = "${convertLongToTime(event.date_start*1000)} - ${convertLongToTime(event.date_finish*1000)}", style = MaterialTheme.typography.bodyMedium)


        }
    }
}

