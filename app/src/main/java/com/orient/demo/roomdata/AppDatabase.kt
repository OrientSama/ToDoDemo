package com.orient.demo.roomdata

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(version = 1, entities = [Event::class])
abstract class AppDatabase: RoomDatabase(){
    abstract fun eventDao():EventDao
    companion object{
        private var instance: AppDatabase ?= null

        @Synchronized //保证多线程安全
        fun getDatabase(context: Context):AppDatabase{
            instance?.let {
                return it
            }
            return Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "app_database")
            .build().apply{
                instance = this
            }
        }
    }
}