package com.orient.demo

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.orient.demo.Data.Event
import com.orient.demo.ui.theme.MyGreen
import com.orient.demo.ui.theme.MyRed
import com.orient.demo.ui.theme.MyYellow

@Composable
fun Cards(event: Event,update:()->Unit) {
    MaterialTheme {
        val typography = MaterialTheme.typography
        val color = if (!event.eventDone) when (event.eventDegree) {
            1 -> MyGreen
            2 -> MyYellow
            else -> MyRed
        } else Color.LightGray
        Surface(
            color = Color.White, modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 8.dp)
                .fillMaxWidth().clickable(onClick = { update() }),
            shape = RoundedCornerShape(10.dp), elevation = 4.dp
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Canvas(modifier = Modifier.size(16.dp)) {
                    drawCircle(color = color)
                }
                if (!event.eventDone) {
                    Text(
                        text = event.eventName,
                        style = typography.subtitle1,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                } else {
                    Text(
                        text = event.eventName,
                        style = typography.subtitle1,
                        modifier = Modifier.padding(start = 16.dp).alpha(0.8f),
                        textDecoration = TextDecoration.LineThrough
                    )
                }

            }
        }

    }
}