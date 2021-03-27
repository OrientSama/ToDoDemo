package com.orient.demo

import android.util.Log
import androidx.lifecycle.ViewModel
import com.orient.demo.roomdata.AppDatabase
import com.orient.demo.roomdata.Event
import kotlinx.coroutines.coroutineScope
import kotlin.concurrent.thread

class MyViewModel : ViewModel() {
    private val eventDao = AppDatabase.getDatabase(MyApplication.context).eventDao()
    fun getEventList() = eventDao.loadAllEvent()

    fun insertEvent(event: Event) {
        thread { eventDao.insertEvent(event) }
    }

    fun updateEvent(newEvent: Event) {
        thread{ eventDao.updateEvent(newEvent)
        }
    }

    fun deleteEvent(eid: Long) {
        eventDao.deleteEventByEid(eid)
    }
}