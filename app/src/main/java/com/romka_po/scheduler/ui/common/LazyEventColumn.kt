package com.romka_po.scheduler.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.LayoutScopeMarker
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.ParentDataModifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.romka_po.scheduler.model.Event
import java.util.TimeZone
import kotlin.math.roundToInt


@Composable
fun TimeLine(
    hoursLabel: @Composable () -> Unit,
    rowCount: Int,
    eventBox: @Composable TimeLineScope.(index: Int) -> Unit,
    modifier: Modifier = Modifier,

    ) {
    val eventBoxes = @Composable {
        repeat(rowCount) {
            TimeLineScope.eventBox(it)
        }
    }
    Layout(
        contents = listOf(hoursLabel, eventBoxes),
        modifier = modifier
    ) { (hoursLabelMeasurables, eventBoxMeasurables), constraints ->
        require(hoursLabelMeasurables.size == 1) {
            "hoursHeader should only emit one composable"
        }
        val hoursLabelPlaceable = hoursLabelMeasurables.first().measure(constraints)

        var totalWidth = hoursLabelPlaceable.width

        val hourBoxHeight = hoursLabelPlaceable.height / 25
        val hourBoxFullHeight = hourBoxHeight * 24

        val eventBoxPlaceables = eventBoxMeasurables.map { measurable ->
            val boxParentData = measurable.parentData as TimeLineParentData
            val boxHeight = (boxParentData.duration * hourBoxFullHeight).roundToInt()
            val boxPlaceable = measurable.measure(
                constraints.copy(
                    minHeight = boxHeight,
                    maxHeight = boxHeight,
                )
            )
            totalWidth += boxPlaceable.width
            boxPlaceable
        }
        val totalHeight = hoursLabelPlaceable.height

        layout(constraints.maxWidth, totalHeight) {
//            var xPosition = hoursLabelPlaceable.width + 20
            var xPosition = 190

            hoursLabelPlaceable.place(0, 0)

            eventBoxPlaceables.forEachIndexed { index, boxPlaceable ->
                val boxParentData = boxPlaceable.parentData as TimeLineParentData
                val boxOffset =
                    (hourBoxHeight / 2 + (boxParentData.offset * hourBoxFullHeight)).roundToInt()
                boxPlaceable.place(xPosition, boxOffset)
//                xPosition += boxPlaceable.width

            }

        }


    }
}


@Composable
fun TimeLineView(list: List<Event>) {
    val scrollableState = rememberScrollState()
    val dialogState: MutableState<Boolean> = remember {mutableStateOf(false) }
    val eventState: MutableState<Event> = remember {mutableStateOf(Event(-1,0,0, "Something Wrong")) }

    if (dialogState.value) {
        Dialog(
            onDismissRequest = { dialogState.value = false },
            content = {
                EventDialog(dialogState = dialogState, event = eventState)
            },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            )
        )
    } else {
//        Toast.makeText(ctx, "Dialog Closed", Toast.LENGTH_SHORT).show()
//        dvm.doSomething()
    }

    TimeLine(
        hoursLabel = { HoursHeader() },
        rowCount = list.size,
        modifier = Modifier
            .verticalScroll(scrollableState, true)
            .padding(horizontal = 4.dp),
        eventBox = { index ->
            val data = list[index]
            EventHolder(
                data, modifier = Modifier
                    .fillMaxWidth(0.8F)
                    .timeLineBar(data.date_start, data.date_finish)
                    .background(
                        Color.Cyan, shape = FloatingActionButtonDefaults.shape
                    )
                    .clickable {
                        eventState.value = data
                        dialogState.value = true
                    }

            )
        },
    )
}


@Composable
private fun HoursHeader() {
    Column(Modifier.padding(horizontal = 8.dp), verticalArrangement = Arrangement.Center) {
        (0..24).forEach {
            Box(
                modifier = Modifier.padding(vertical = 4.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 50.dp)
                )
                Text(
                    text = if (it < 10) "0$it:00" else ("$it:00"),
                    modifier = Modifier,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

        }
    }
}

@LayoutScopeMarker
@Immutable
object TimeLineScope {
    @Stable
    fun Modifier.timeLineBar(
        startTime: Long,
        finishTime: Long
    ): Modifier {
        return then(
            TimeLineParentData(

                duration = (finishTime - startTime) / 3600 / 24F,
                offset = ((startTime + TimeZone.getDefault().rawOffset / 1000) % 86400 / 3600) / 24F
            )
        )
    }
}

class TimeLineParentData(
    val duration: Float,
    val offset: Float,
) : ParentDataModifier {
    override fun Density.modifyParentData(parentData: Any?) = this@TimeLineParentData
}


fun check(): MutableList<Event> {
    val list = mutableListOf<Event>()
    list.add(Event(1, 1681750800, 1681772400, "Hello", null))
    return list
}

@Preview
@Composable
fun TimeLineViewTest() {
    TimeLineView(list = check())
}
