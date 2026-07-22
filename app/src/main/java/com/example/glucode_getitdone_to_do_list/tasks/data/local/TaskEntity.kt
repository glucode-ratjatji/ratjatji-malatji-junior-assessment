package com.example.glucode_getitdone_to_do_list.tasks.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity//(tableName = "todo_weather")
data class TaskEntity (
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "task_title" ) val title: String,
    @ColumnInfo(name = "task_description" ) val description: String,
    @ColumnInfo(name = "task_complete" ) val isComplete: Boolean = false
)