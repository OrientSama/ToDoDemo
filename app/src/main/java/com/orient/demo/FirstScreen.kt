package com.orient.demo

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp

@ExperimentalFoundationApi
@Composable
fun FirstScreen(viewModel: MyViewModel, showEdit: () -> Unit) {
    val events by viewModel.getEventList().observeAsState()
    val scaffoldState = rememberScaffoldState()
    Scaffold(scaffoldState = scaffoldState,
        topBar = {
            Column{
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 50.dp, bottom = 4.dp)
                ) {
                    Text(
                        modifier = Modifier.padding(start = 20.dp),
                        text = "待办",
                        style = MaterialTheme.typography.h5,
                        color = MaterialTheme.colors.onBackground
                    )
                }
                Divider(thickness = 0.8.dp)
                //增加分界线
            }
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(text = {
                Text(
                    text = "创建",
                    color = MaterialTheme.colors.onSecondary
                )
            },
                onClick = {
                    showEdit()
                })
        }, floatingActionButtonPosition = FabPosition.End,
        content = {
            Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                if(events != null && events?.isNotEmpty() == true){
                    LazyColumn {
                        items(events!!, key = { event -> event.eid }) { event ->
                            Cards(event,
                                update = {
                                    //event.eventDone = !event.eventDone
                                    //修改自身可能导致了UI不刷新
                                    //修改event不会导致重组，LiveData的变更才会带来重组
                                    val newEvent = event.copy(eventDone = !event.eventDone)
                                    viewModel.updateEvent(newEvent)
                                },
                                delete = {
                                    viewModel.deleteEvent(event.eid)
                                    Toast.makeText(MyApplication.context, "已删除", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            )
                        }
                    }

                } else {
                    Box(modifier = Modifier
                        .padding(bottom = 40.dp)
                        .fillMaxSize(),
                        contentAlignment = Alignment.Center) {
                        Text(text = "这里空空如也~\n\n1.点击卡片完成任务\n2.长按卡片删除任务",Modifier.alpha(0.5f))
                    }
                }
            }
        }
    )
}


