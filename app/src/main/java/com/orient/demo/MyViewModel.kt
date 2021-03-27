package com.orient.demo

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.orient.demo.Data.AppDatabase
import com.orient.demo.Data.Event
import kotlin.concurrent.thread

class MyViewModel:ViewModel() {
    val eventDao = AppDatabase.getDatabase(MyApplication.context).eventDao()
    fun getEventList()= eventDao.loadAllEvent()

    fun insertEvent(event: Event){
        thread { eventDao.insertEvent(event) }
    }

    fun updateEventByDone(newEvent: Event){
        thread { eventDao.updateEvent(newEvent) }
    }
    fun deleteEvent(eid:Long){
        eventDao.deleteEventByEid(eid)
    }
}