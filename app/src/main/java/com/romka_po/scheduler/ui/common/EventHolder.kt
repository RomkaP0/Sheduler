package com.romka_po.scheduler.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.romka_po.scheduler.model.Event

@Composable
fun EventHolder(
    event: Event,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.padding(start = 4.dp)
    ) {
        Text(text = event.name, style = MaterialTheme.typography.headlineSmall)
//        timestampUtils.apply {
//            Text(text = "${transToString(event.date_start)} - ${transToString(event.date_finish)}", style = MaterialTheme.typography.bodyMedium)
//
//
//        }
    }
}

@Preview
@Composable
fun test(){
    EventHolder(event = check()[0], Modifier.sizeIn(minHeight = 64.dp))
}
