package com.example.glucode_getitdone_to_do_list.tasks.data.TaskViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.glucode_getitdone_to_do_list.tasks.data.local.Task
import com.example.glucode_getitdone_to_do_list.tasks.domain.repository.ITaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@HiltViewModel // This tells Hilt to manage this ViewModel
class TaskViewModel @Inject constructor(
    private val repository: ITaskRepository
) : ViewModel() {

    // The ViewModel collects the data from the repository and prepares it for Compose
    val tasks = repository.getAllTasks()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    // 2. Writing data (Triggered by the UI)
    @OptIn(ExperimentalUuidApi::class)
    fun addTask(title: String, description: String) {
        // viewModelScope.launch starts a background thread so the UI doesn't freeze
        viewModelScope.launch {
            val newTask = Task(
                title = title,
                description = description,
                isComplete = false
            )
            repository.insertTask(newTask)
        }
    }

    // 3. Deleting data (Triggered by a swipe or button in the UI)
    fun deleteTask(task: Task) {
        viewModelScope.launch {
            repository.deleteTask(task)
        }
    }

    fun onTaskUpdated(task: Task, isChecked: Boolean){//newTitle: String, newDescription: String, ){
        viewModelScope.launch{
            val updatedTask = task.copy(
//                title = newTitle,
//                description = newDescription,
                isComplete = isChecked
            )
            repository.updateTask(updatedTask)
        }
    }
}