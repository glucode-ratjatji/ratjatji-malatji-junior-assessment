package com.example.glucode_getitdone_to_do_list.tasks.presentation.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Album
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.glucode_getitdone_to_do_list.tasks.data.TaskViewModel.TaskViewModel
import com.example.glucode_getitdone_to_do_list.tasks.data.local.Task
import com.example.glucode_getitdone_to_do_list.tasks.data.local.TaskFilter

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ToDoTab(taskViewModel: TaskViewModel,
            onTaskTap: (Task) -> Unit,
            onTaskLongClick: (Task) -> Unit,
            onCheckedChanged: (Task) -> Unit
){
    // 2. Observe the state safely aware of the lifecycle
    val currentFilter by taskViewModel.currentFilter.collectAsStateWithLifecycle()
    val visibleTasks by taskViewModel.visibleTasks.collectAsStateWithLifecycle()

    Scaffold {
        Column {
            PrimaryTabRow(
                selectedTabIndex = currentFilter.ordinal
            ) {
                TaskFilter.entries.forEach { filter ->
                    Tab(
                        selected = currentFilter == filter,
                        onClick = {
                            // Tell the ViewModel the user clicked a tab
                            taskViewModel.updateFilter(filter)
                        },
                        text = {
                            Text(
                                text = filter.label,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    )
                }
            }

            //no tasks in the visibleTasks list
            if (visibleTasks.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "There are no tasks that are under ${currentFilter.label.lowercase()}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            } else {
            //If there are tasks, they show here
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(visibleTasks, key = { it.id }) { task ->
                        CheckableCardComponent(
                            title = task.title,
                            description = task.description,
                            isChecked = task.isComplete,
                            onCheckedChanged = { newIsCheckedStatus ->
                                val toggledTask = task.copy(isComplete = newIsCheckedStatus)
                                taskViewModel.onTaskUpdated(toggledTask)
                            },
                            onLongClick = {
                                onTaskLongClick(task)
                            },
                            onTap = {
                                onTaskTap(task)
                            },
                            onEdit = {}
                        )
                    }
                }
            }
        }
    }
}
