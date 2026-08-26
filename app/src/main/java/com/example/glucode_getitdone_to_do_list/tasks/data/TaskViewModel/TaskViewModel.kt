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

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val repository: ITaskRepository,
    private val filterTaskUseCase: FilterTaskUseCase = FilterTaskUseCase()
) : ViewModel() {

    private val _currentFilter = MutableStateFlow(TaskFilter.TO_DO)
    val currentFilter: StateFlow<TaskFilter> = _currentFilter.asStateFlow()

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

    fun addTask(title: String, description: String) {
        viewModelScope.launch {
            val newTask = Task(
                title = title,
                description = description,
                isComplete = false
            )
            repository.insertTask(newTask)
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            repository.deleteTask(task)
        }
    }

    fun onTaskUpdated(updatedTask: Task) {
        viewModelScope.launch {
            repository.updateTask(updatedTask)
        }
    }

    fun updateFilter(newFilter: TaskFilter) {
        _currentFilter.value = newFilter
    }
}