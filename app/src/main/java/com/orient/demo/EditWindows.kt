package com.orient.demo

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.orient.demo.roomdata.Event
import com.orient.demo.ui.theme.MyGreen
import com.orient.demo.ui.theme.MyRed
import com.orient.demo.ui.theme.MyYellow
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
@Composable
fun EditWindow(viewModel: MyViewModel, noEdit: () -> Unit) {
    var text by remember { mutableStateOf("") }
    var checked by remember { mutableStateOf(1) }
    val (color1, color2, color3) = when (checked) {
        1 -> ColorState(MyGreen, Color.LightGray, Color.LightGray)
        2 -> ColorState(Color.LightGray, MyYellow, Color.LightGray)
        else -> ColorState(Color.LightGray, Color.LightGray, MyRed)
    }
    Surface(
        Modifier
            .background(Color.Transparent)
            .fillMaxWidth(), shape = RoundedCornerShape(10.dp)
    ) {

        Column {
            Row(Modifier.padding(top = 12.dp, start = 12.dp, bottom = 2.dp)) {
                Box(modifier = Modifier
                    .size(20.dp)
                    .clip(RoundedCornerShape(50))
                    .background(color1)
                    .clickable {
                        checked = 1
                    }
                )
                Box(modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
                    .size(20.dp)
                    .clip(RoundedCornerShape(50))
                    .background(color2)
                    .clickable {
                        checked = 2
                    }
                )
                Box(modifier = Modifier
                    .size(20.dp)
                    .clip(RoundedCornerShape(50))
                    .background(color3)
                    .clickable {
                        checked = 3
                    }
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { noEdit() }, modifier = Modifier.size(20.dp)) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_close),
                        contentDescription = "Close the window",
                        tint = Color.Gray
                    )
                }
                IconButton(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 12.dp)
                        .size(20.dp),
                    onClick = {
                        val simpleDateFormat = SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss") // HH:mm:ss
                        val date = Date(System.currentTimeMillis())
                        val time = simpleDateFormat.format(date)
                        if (text.isNotEmpty()) {
                            viewModel.insertEvent(Event(text, checked, time, false))
                            noEdit()
                        } else {
                            Toast.makeText(MyApplication.context, "请输入名称", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_done),
                        contentDescription = "Save the task",
                        tint = Color.Gray
                    )
                }
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, end = 12.dp, bottom = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text(text = "待办名称") },
                    modifier = Modifier.weight(0.9f),
                    singleLine = true
                )
            }

        }
    }
}

data class ColorState(var Color1: Color, var Color2: Color, var Color3: Color)//用来显示任务难度