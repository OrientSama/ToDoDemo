package com.orient.demo

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun FirstScreen(viewModel: MyViewModel, showEdit: () -> Unit) {
    val events by viewModel.getEventList().observeAsState()
    val scaffoldState = rememberScaffoldState()
    //?
    Scaffold(scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(backgroundColor = Color.White) {
                Text(
                    modifier = Modifier.padding(start = 12.dp),
                    text = "待办",
                    style = MaterialTheme.typography.h6
                )

            }
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(text = { Text(text = "创建", color = Color.White) },
                backgroundColor = Color.Black,
                onClick = {
                    showEdit()
                })
        }, floatingActionButtonPosition = FabPosition.End,
        content = {
            Surface(color = Color.White, modifier = Modifier.fillMaxSize()) {
                val scrollState = rememberScrollState()
                events?.let {
                    Column(modifier = Modifier.verticalScroll(scrollState)) {
                        for (event in events!!) {
                            Cards(event) {
                                event.eventDone = !event.eventDone
                                viewModel.updateEvent(event)
                            }
                        }
                    }
                }
            }
        }
    )
}


