package com.example.glucode_getitdone_to_do_list.tasks.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid


@Entity//(tableName = "todo_weather")
data class Task @OptIn(ExperimentalUuidApi::class) constructor(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "task_title" ) val title: String,
    @ColumnInfo(name = "task_description" ) val description: String,
    @ColumnInfo(name = "task_complete" ) val isComplete: Boolean = false
)