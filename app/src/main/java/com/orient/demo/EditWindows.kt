package com.orient.demo

import android.annotation.SuppressLint
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.orient.demo.Data.Event
import com.orient.demo.ui.theme.MyGreen
import com.orient.demo.ui.theme.MyRed
import com.orient.demo.ui.theme.MyYellow
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
@Composable
fun EditWindow(showEdit: ()-> Unit) {
    val viewModel:MyViewModel = viewModel()
    var text by remember{ mutableStateOf("") }
    var checked by remember { mutableStateOf(1) }
    var (color1,color2,color3) = when(checked){
        1 -> ColorState(MyGreen,Color.Gray,Color.Gray)
        2 -> ColorState(Color.Gray, MyYellow,Color.Gray)
        else -> ColorState(Color.Gray,Color.Gray, MyRed)
    }
    Surface(
        Modifier
            .background(Color.Transparent)
            .fillMaxWidth(),shape = RoundedCornerShape(10.dp)) {

            Column {Row (Modifier.padding(top = 16.dp,start = 16.dp,bottom = 2.dp)){
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
            }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),verticalAlignment = Alignment.CenterVertically) {
                OutlinedTextField(value = text,
                    onValueChange = { text = it },
                    label = { Text(text = "待办名称")},
                    modifier = Modifier.weight(0.85f)
                )
                IconButton(onClick = {
                    showEdit()
                    val simpleDateFormat = SimpleDateFormat("yyyy年MM月dd日 HH:mm") // HH:mm
                    val date = Date(System.currentTimeMillis())
                    val time = simpleDateFormat.format(date)
                    viewModel.insertEvent(Event(text,checked,time,false))
                                     },modifier = Modifier.weight(0.15f)) {
                    val icon = painterResource(id = R.drawable.ic_done)
                    Icon(painter = icon, contentDescription = "Done",modifier = Modifier.fillMaxSize(0.8f))
                }
                }

            }
    }
}

data class ColorState(var Color1:Color, var Color2: Color,var Color3: Color)

@Preview(showBackground = true)
@Composable
fun editDemo(){
    EditWindow {  }
}
