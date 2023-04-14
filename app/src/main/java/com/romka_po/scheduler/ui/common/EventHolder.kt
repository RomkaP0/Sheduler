package com.romka_po.scheduler.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.romka_po.scheduler.model.Event

@Composable
fun EventHolder(
    event: Event,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier
        .fillMaxWidth()
        .padding(2.dp, 4.dp)
        .background(Color.Magenta, shape = RoundedCornerShape(4.dp))
    ) {
        Text(text = "Meeting", style = MaterialTheme.typography.headlineMedium)
        Text(text = "21:00 - 23:00", style = MaterialTheme.typography.bodyMedium)
    }
}

@Preview
@Composable
fun test(){
    EventHolder(event = check()[0], Modifier.sizeIn(minHeight = 64.dp))
}

fun check(): MutableList<Event> {
    val list = mutableListOf<Event>()
    for (i in 0 until 2)
        list.add(Event(1,34434,43343, "Hello",  null))
    return list
}