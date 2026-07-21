package com.example.glucode_getitdone_to_do_list.tasks.data.repository

import com.example.glucode_getitdone_to_do_list.tasks.data.local.Task
import com.example.glucode_getitdone_to_do_list.tasks.data.local.TaskDao
import com.example.glucode_getitdone_to_do_list.tasks.domain.repository.ITaskRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class TaskRepositoryImpl @Inject constructor(
    private val taskDao: TaskDao
) : ITaskRepository {

    // 3. Here is where your dao.getAll() goes
    override fun getAllTasks(): Flow<List<Task>> {
        return taskDao.getAll()
    }

    override suspend fun insertTask(newTask: Task): Flow<List<Task>> {
        taskDao.insertAll(newTask)
        return getAllTasks()
    }

    override suspend fun deleteTask(task: Task): Flow<List<Task>> {
        taskDao.delete(task)
        return getAllTasks()
    }
}