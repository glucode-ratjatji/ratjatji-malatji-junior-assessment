package com.example.glucode_getitdone_to_do_list.tasks.data.TaskViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.glucode_getitdone_to_do_list.tasks.domain.FilterTaskUseCase
import com.example.glucode_getitdone_to_do_list.tasks.data.local.Task
import com.example.glucode_getitdone_to_do_list.tasks.data.local.TaskFilter
import com.example.glucode_getitdone_to_do_list.tasks.domain.repository.ITaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlin.uuid.ExperimentalUuidApi

@HiltViewModel // This tells Hilt to manage this ViewModel
class TaskViewModel @Inject constructor(
    private val repository: ITaskRepository,
    private val filterTaskUseCase: FilterTaskUseCase = FilterTaskUseCase()
) : ViewModel() {

    // 1. Hold the current filter state
    private val _currentFilter = MutableStateFlow(TaskFilter.TO_DO)
    val currentFilter: StateFlow<TaskFilter> = _currentFilter.asStateFlow()

//    // The ViewModel collects the data from the repository and prepares it for Compose
//    val tasks = repository.getAllTasks()
//        .stateIn(
//            viewModelScope,
//            SharingStarted.WhileSubscribed(5000),
//            emptyList()
//        )

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

    val visibleTasks: StateFlow<List<Task>> = combine(
        repository.getAllTasks(),
        _currentFilter
    ) { rawTasks, filterType ->
        filterTaskUseCase(rawTasks, filterType)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    // 3. Deleting data (Triggered by a swipe or button in the UI)
    fun deleteTask(task: Task) {
        viewModelScope.launch {
            repository.deleteTask(task)
        }
    }

    fun onTaskUpdated(updatedTask: Task){
        viewModelScope.launch{
            repository.updateTask(updatedTask)
        }
    }

    fun updateFilter(newFilter: TaskFilter) {
        _currentFilter.value = newFilter
    }

}