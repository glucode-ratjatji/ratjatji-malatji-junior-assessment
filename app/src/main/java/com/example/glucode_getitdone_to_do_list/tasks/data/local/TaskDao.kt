package com.example.glucode_getitdone_to_do_list.tasks.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

//@Dao
//class TaskDao {
//    @Query("SELECT * FROM task")
//    fun getAll(): List<Task>
//
//    @Query("SELECT * FROM task WHERE uid IN (:taskIds)")
//    fun loadAllByIds(taskIds: IntArray): List<Task>
//
//    @Query("SELECT * FROM task WHERE task_title LIKE :title LIMIT 1")
//    fun findByTitle(title: String): Task
//
//    @Insert
//    fun insertAll(vararg task: Task)
//
//    @Delete
//    fun delete(task: Task)
//}