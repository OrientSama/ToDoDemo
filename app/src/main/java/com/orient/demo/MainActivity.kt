package com.orient.demo

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.orient.demo.ui.theme.DemoTheme


class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MyViewModel>()

    @ExperimentalFoundationApi
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            val viewModel: MyViewModel = viewModel()
            var showEdit by remember { mutableStateOf(0) }
            Box(contentAlignment = Alignment.Center) {
                DemoTheme {
                    FirstScreen(viewModel) { showEdit = 1 }
                    if (showEdit == 1) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize(1f)
                                .background(Color.Black.copy(alpha = 0.3f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Box(Modifier.padding(start = 16.dp, end = 16.dp)) {
                                EditWindow(viewModel) { showEdit = 0 }
                            }
                        }

                    }
                }
            }
        }
    }

}