package com.example.glucode_getitdone_to_do_list.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.glucode_getitdone_to_do_list.tasks.data.local.TaskDao
import com.example.glucode_getitdone_to_do_list.tasks.data.local.Task

@Database(entities = [Task::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
   abstract fun taskDao(): TaskDao
}