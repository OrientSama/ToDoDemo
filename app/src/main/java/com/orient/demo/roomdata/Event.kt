package com.orient.demo.roomdata

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Event(
    val eventName: String,
    val eventDegree: Int,
    val eventCreateTime: String,
    val eventDone: Boolean,
    @PrimaryKey(autoGenerate = true)  val eid: Long = 0
//data class 非必要的话，不用var
)