package com.orient.demo.roomdata

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface EventDao {

    @Insert
    fun insertEvent(event: Event): Long

    @Update
    fun updateEvent(newEvent: Event)

    @Query("select * from Event order by eventCreateTime desc")
    //先按照 后创建的放在最前面
        fun loadAllEvent(): LiveData<List<Event>>

    @Query("delete from Event where eid = :eid")
    fun deleteEventByEid(eid: Long): Int
    //通过使用eid来删除任务
}