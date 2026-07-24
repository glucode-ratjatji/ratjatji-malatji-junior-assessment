package com.example.glucode_getitdone_to_do_list.tasks.domain

import com.example.glucode_getitdone_to_do_list.tasks.data.local.Task
import com.example.glucode_getitdone_to_do_list.tasks.data.local.TaskFilter
import javax.inject.Inject

class FilterTaskUseCase @Inject constructor() {
    //filterType.TO_DO can be called as a function and return the correct list
    operator fun invoke(tasks: List<Task>, filterType: TaskFilter): List<Task> {
        return when (filterType) {
            TaskFilter.TO_DO -> tasks.filter { !it.isComplete }
            TaskFilter.COMPLETED -> tasks.filter { it.isComplete }
        }
    }
}