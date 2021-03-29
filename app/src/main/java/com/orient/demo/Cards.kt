package com.orient.demo

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.orient.demo.roomdata.Event
import com.orient.demo.ui.theme.MyGreen
import com.orient.demo.ui.theme.MyRed
import com.orient.demo.ui.theme.MyYellow

@ExperimentalFoundationApi
@Composable
fun Cards(event: Event, update: () -> Unit, delete: () -> Unit) {
    val typography = MaterialTheme.typography
    val color = if (!event.eventDone) when (event.eventDegree) {
        1 -> MyGreen
        2 -> MyYellow
        else -> MyRed
    } else Color.LightGray

    Surface(
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp, top = 6.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .combinedClickable(onClick = update, onLongClick = delete),
        color = MaterialTheme.colors.surface
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Canvas(
                modifier = Modifier
                    .width(10.dp)
                    .height(30.dp)
            ) {
                drawLine(
                    color = color,
                    start = Offset(14f, 12.5f),
                    end = Offset(14f, 70.5f),
                    strokeWidth = 25f,
                    cap = StrokeCap.Round
                )
            }
            if (!event.eventDone) {
                Text(
                    text = event.eventName,
                    style = typography.subtitle1,
                    modifier = Modifier.padding(start = 12.dp),
                    color = MaterialTheme.colors.onSurface
                )
            } else {
                Text(
                    text = event.eventName,
                    style = typography.subtitle1,
                    modifier = Modifier
                        .padding(start = 12.dp)
                        .alpha(0.8f),
                    textDecoration = TextDecoration.LineThrough,
                    color = MaterialTheme.colors.onSurface
                )
            }

        }
    }
}
