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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.orient.demo.ui.theme.DemoTheme


class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MyViewModel>()

    @ExperimentalFoundationApi
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var visibleEdit by remember { mutableStateOf(false) }
            Box(contentAlignment = Alignment.Center) {
                DemoTheme {
                    FirstScreen(viewModel) { visibleEdit = true }
                    if (visibleEdit) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize(1f)
                                .background(Color.Black.copy(alpha = 0.15f)),
                            contentAlignment = Alignment.BottomCenter
                        ) {
                            Box {
                                EditWindow(viewModel) { visibleEdit = false }
                            }
                        }

                    }
                }
            }
        }
    }

}