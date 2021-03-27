package com.orient.demo.Data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Event(
    var eventName: String,
    var eventDegree: Int,
    var eventCreateTime: String,
    var eventDone: Boolean
) {
    @PrimaryKey(autoGenerate = true)
    var eid: Long = 0
}