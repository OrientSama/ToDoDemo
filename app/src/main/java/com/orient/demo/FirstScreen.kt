package com.orient.demo

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.orient.demo.Data.Event


@Composable
fun FirstScreen(showEdit:()->Unit){
    val viewModel:MyViewModel = viewModel()
    val list = viewModel.getEventList().observeAsState()
    val scaffoldState = rememberScaffoldState()
    //?
    Scaffold(scaffoldState = scaffoldState,
        topBar={
            TopAppBar(backgroundColor = Color.White) {
                Text(modifier = Modifier.padding(start = 16.dp),text = "待办",style = MaterialTheme.typography.h6)

            }
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(text = { Text(text = "创建",color = Color.White) },
                backgroundColor = Color.Black,
                onClick = {
                    showEdit()
                })
        },floatingActionButtonPosition = FabPosition.End,
        content = {
            Surface(color = Color.White,modifier = Modifier.fillMaxSize()) {
                if (list.value != null){
                    Column {
                        for (event in list.value!!){
                            Cards(event_name = event.eventName, degree = event.eventDegree,event_done = event.eventDone,eid = event.eid){
                                event.eventDone = true
                                viewModel.updateEventByDone(event)}
                        }
                    }
                } else {
                    Text(text = "空空如也~",Modifier.alpha(0.5f))
                }
            }
        }
    )
}

@Composable
fun Cards(event_name: String,degree: Int,event_done:Boolean,eid:Long,update:()->Unit) {
    MaterialTheme {
        val typography = MaterialTheme.typography
        val color = if(!event_done)when(degree){
            1 -> com.orient.demo.ui.theme.MyGreen
            2 -> com.orient.demo.ui.theme.MyYellow
            else -> com.orient.demo.ui.theme.MyRed
        } else androidx.compose.ui.graphics.Color.LightGray
        Surface(color = Color.White,modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 8.dp)
            .fillMaxWidth().clickable(onClick = { update() }),
            shape = RoundedCornerShape(10.dp),elevation = 4.dp) {
            Row(modifier = Modifier.padding(16.dp),verticalAlignment = Alignment.CenterVertically){
                Canvas(modifier = Modifier.size(16.dp)) {
                    drawCircle(color = color)
                }
                if (!event_done) {
                    Text(
                        text = event_name,
                        style = typography.subtitle1,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }else {
                    Text(
                        text = event_name,
                        style = typography.subtitle1,
                        modifier = Modifier.padding(start = 16.dp).alpha(0.8f),
                        textDecoration = androidx.compose.ui.text.style.TextDecoration.Companion.LineThrough
                    )
                }

            }
        }

    }
}
