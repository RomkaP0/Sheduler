package com.romka_po.scheduler.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.LayoutScopeMarker
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.ParentDataModifier
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
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
            val xPosition = 190

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
fun TimeLineView(list: List<Event?>,
                 startTime: Long = 0,
                 dropEvent:((Event) -> Unit)?,
                 dialogState: MutableState<Boolean>,
                 eventState: MutableState<Event>) {
    val scrollableState = rememberScrollState()



    if (dialogState.value) {
        Dialog(
            onDismissRequest = { dialogState.value = false }
        ) {
            EventDialog(dialogState = dialogState, event = eventState.value, dropEvent)
        }
    }
    var finishTimeWithOffset: Long = Long.MAX_VALUE
    var startTimeWithOffset:Long =0
    if (startTime != 0.toLong()) {
        startTimeWithOffset = startTime - TimeZone.getDefault().rawOffset
        finishTimeWithOffset = startTimeWithOffset + 86400000

    }
    TimeLine(
        hoursLabel = { HoursHeader() },
        rowCount = list.size,
        modifier = Modifier
            .verticalScroll(scrollableState, true)
            .padding(horizontal = 4.dp),
        eventBox = { index ->
            val data = list[index]
            if (data != null) {
                if (!((data.date_start>=finishTimeWithOffset)||(data.date_finish<=startTimeWithOffset)))
                    EventHolder(
                        data, modifier = Modifier
                            .fillMaxWidth(0.8F)
                            .timeLineBar(
                                data.date_start,
                                data.date_finish,
                                startTimeWithOffset,
                                finishTimeWithOffset
                            )

                            .background(
                                color = MaterialTheme.colorScheme.surface,
                                shape = CardDefaults.outlinedShape
                            )
                            .border(
                                width = 4.dp,
                                color = MaterialTheme.colorScheme.primaryContainer,
                                shape = CardDefaults.outlinedShape
                            )
                            .clickable {
                                eventState.value = data
                                dialogState.value = true
                            }

                    )
            }
        },
    )
}
@LayoutScopeMarker
@Immutable
object TimeLineScope {
    @Stable
    fun Modifier.timeLineBar(
        startTime: Long,
        finishTime: Long,
        startDayTime:Long,
        finishDayTime:Long,
    ): Modifier {

        val st = if ((startTime)<startDayTime) startDayTime else (startTime )
        val fin = if ((finishTime)>finishDayTime) finishDayTime else (finishTime)

        return then(
            TimeLineParentData(

                duration = (fin - st) / 1000 / 3600F / 24F,
                offset = ((((st +TimeZone.getDefault().rawOffset)/ 1000) % 86400) / 3600F) / 24F
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

