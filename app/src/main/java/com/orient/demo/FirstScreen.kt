package com.orient.demo

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@ExperimentalFoundationApi
@Composable
fun FirstScreen(viewModel: MyViewModel, showEdit: () -> Unit) {
    val events by viewModel.getEventList().observeAsState()
    val scaffoldState = rememberScaffoldState()
    Scaffold(scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(elevation = 0.dp, backgroundColor = MaterialTheme.colors.background) {
                Text(
                    modifier = Modifier.padding(start = 12.dp),
                    text = "待办",
                    style = MaterialTheme.typography.h5,
                    color = MaterialTheme.colors.onBackground
                )

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
                events?.let {
                    LazyColumn() {
                        items(events!!, key = { event -> event.eid }) { event ->
                            Cards(event,
                                update = {
                                    event.eventDone = !event.eventDone
                                    viewModel.updateEvent(event)
                                    Log.d("UI", "FirstScreen: 已提交${event.eventName}")
                                },
                                delete = {
                                    viewModel.deleteEvent(event.eid)
                                    Toast.makeText(MyApplication.context, "已删除", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            )
                        }
                    }
                }
            }
        }
    )
}


