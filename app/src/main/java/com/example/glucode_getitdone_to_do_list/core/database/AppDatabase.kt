package com.example.glucode_getitdone_to_do_list.core.database

import androidx.room.Database
import com.example.glucode_getitdone_to_do_list.tasks.data.local.TaskDao
import com.example.glucode_getitdone_to_do_list.tasks.data.local.TaskEntity

@Database(entities = [TaskEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}