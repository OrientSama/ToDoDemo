package com.orient.demo

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class MyApplication:Application() {
    //全局获取context
    //使用MyApplicaton.context 获得
    companion object{

        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}