package com.example.glucode_getitdone_to_do_list.tasks.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM task")
    fun getAll(): Flow<List<Task>>

    @Query("SELECT * FROM task WHERE id IN (:taskIds)")
    fun loadAllByIds(taskIds: Int): List<Task>

    @Query("SELECT * FROM task WHERE task_title LIKE :title LIMIT 1")
    fun findByTitle(title: String): Task

    @Insert
     fun insertAll(vararg task: Task)

    @Delete
     fun delete(task: Task)
}