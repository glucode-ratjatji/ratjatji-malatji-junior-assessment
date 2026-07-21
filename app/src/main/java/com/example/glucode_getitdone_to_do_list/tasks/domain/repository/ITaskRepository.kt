package com.example.glucode_getitdone_to_do_list.tasks.domain.repository

import com.example.glucode_getitdone_to_do_list.tasks.data.local.Task
import kotlinx.coroutines.flow.Flow

interface ITaskRepository {

    fun getAllTasks(): Flow<List<Task>>
    suspend fun insertTask(newTask: Task): Flow<List<Task>>
    suspend fun deleteTask(task: Task): Flow<List<Task>>
}