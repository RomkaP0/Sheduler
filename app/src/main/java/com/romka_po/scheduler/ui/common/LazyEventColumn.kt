package com.romka_po.scheduler.ui.common

import androidx.compose.foundation.layout.LayoutScopeMarker
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.ParentDataModifier
import androidx.compose.ui.unit.Density
import com.romka_po.scheduler.model.Event
import java.util.TimeZone
import kotlin.math.roundToInt




@Composable
fun TimeLine(
    hoursLabel: @Composable () -> Unit,
    rowCount: Int,
    eventBox: @Composable TimeLineScope.(index: Int) -> Unit,
    modifier: Modifier = Modifier
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


        val eventBoxPlaceables = eventBoxMeasurables.map { measurable ->
            val boxParentData = measurable.parentData as TimeLineParentData
            val boxHeight = (boxParentData.duration * hoursLabelPlaceable.height).roundToInt()
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
            var xPosition = hoursLabelPlaceable.width+20

            hoursLabelPlaceable.place(0, 0)

            eventBoxPlaceables.forEachIndexed { index, boxPlaceable ->
                val boxParentData = boxPlaceable.parentData as TimeLineParentData
                val boxOffset = (boxParentData.offset * hoursLabelPlaceable.height).roundToInt()
                boxPlaceable.place(xPosition, boxOffset)
//                xPosition += boxPlaceable.width

            }

        }


    }
}

class TimeLineParentData(
    val duration: Float,
    val offset: Float,
) : ParentDataModifier {
    override fun Density.modifyParentData(parentData: Any?) = this@TimeLineParentData
}


@LayoutScopeMarker
@Immutable
object TimeLineScope {
    @Stable
    fun Modifier.timeLineBar(
        startTime:Long,
        finishTime:Long
    ): Modifier {
        return then(
            TimeLineParentData(

                duration = (finishTime-startTime)/3600/24F,
                offset = ((startTime+TimeZone.getDefault().rawOffset/1000)%86400/3600)/24F
            )
        )
    }
}




fun check(): MutableList<Event> {
    val list = mutableListOf<Event>()
    list.add(Event(1, 1681581749, 1681648738, "Hello", null))
    list.add(Event(2, 1681650150, 1681657350, "World", null))
    return list
}
