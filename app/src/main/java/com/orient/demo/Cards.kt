package com.orient.demo

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.orient.demo.roomdata.Event
import com.orient.demo.ui.theme.MyGreen
import com.orient.demo.ui.theme.MyRed
import com.orient.demo.ui.theme.MyYellow

@Composable
fun Cards(event: Event, update: () -> Unit) {
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
            .clickable(onClick = { update() })
            .clip(RoundedCornerShape(12.dp)),
        color = Color.White
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
                    modifier = Modifier.padding(start = 12.dp)
                )
            } else {
                Text(
                    text = event.eventName,
                    style = typography.subtitle1,
                    modifier = Modifier
                        .padding(start = 12.dp)
                        .alpha(0.8f),
                    textDecoration = TextDecoration.LineThrough
                )
            }

        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun default() {
//    Cards(event = Event("哭泣的黑曜石", 3, "2021,3,28", false)) {
//    }
//}