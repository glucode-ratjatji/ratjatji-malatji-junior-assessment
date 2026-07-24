package com.example.glucode_getitdone_to_do_list.tasks.presentation.MainDashboardScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.glucode_getitdone_to_do_list.tasks.data.TaskViewModel.TaskViewModel
import com.example.glucode_getitdone_to_do_list.tasks.data.local.Task
import com.example.glucode_getitdone_to_do_list.tasks.presentation.components.BottomSheet
import com.example.glucode_getitdone_to_do_list.tasks.presentation.components.CheckableCardComponent
import com.example.glucode_getitdone_to_do_list.tasks.presentation.components.FABContent
import com.example.glucode_getitdone_to_do_list.tasks.presentation.components.ToDoTab
import com.example.glucode_getitdone_to_do_list.tasks.presentation.components.WeatherCardComponent
import com.example.glucode_getitdone_to_do_list.weather.domain.model.WeatherResponse
import com.example.glucode_getitdone_to_do_list.weather.presentation.WeatherViewModel.WeatherViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainDashboardScreen(navController: NavController,
                        viewModel: WeatherViewModel,
                        taskViewModel: TaskViewModel = hiltViewModel()
) {
    val taskList by taskViewModel.tasks.collectAsState()
    var taskToDelete by remember { mutableStateOf<Task?>(null) }
    var taskToUpdate by remember { mutableStateOf<Task?>(null) }
    val weatherData by viewModel.weatherData.collectAsState<WeatherResponse?>()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()
    var showBottomSheet by remember { mutableStateOf(false) }

    // 2. Trigger the fetch instantly on launch
    LaunchedEffect(Unit) {
        viewModel.fetchWeather("Sandton")
    }
    Scaffold(
        floatingActionButton = {
            FABContent(onTap = { showBottomSheet = true })
        },
        topBar = {
            TopAppBar(
                title =
                    { Text("To-Do Weather App", style = MaterialTheme.typography.headlineMedium) }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            WeatherCardComponent(
                weatherResponse = weatherData,
                isLoading = isLoading,
                error = error
            )
            Spacer(modifier = Modifier.height(16.dp))
            if (showBottomSheet) {
                BottomSheet(
                    taskToEdit = taskToUpdate,
                    onDismiss = { showBottomSheet = false
                    taskToUpdate = null}
                )
            }

            ToDoTab() {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // loop through every task in your db that are NOT complete
                    items(taskList) { task ->
      //                  if (!task.isComplete) {
                            Column() {
                                CheckableCardComponent(
                                    title = task.title,
                                    description = task.description,
                                    isChecked = task.isComplete,
                                    onCheckedChanged = { newIsCheckedStatus ->
                                        val toggledTask = task.copy(isComplete = newIsCheckedStatus)
                                        taskViewModel.onTaskUpdated(toggledTask)
                                    },
                                    onLongClick = {
                                        taskToDelete = task
                                    },
                                    onTap = { showBottomSheet = true ; taskToUpdate = task},
                                    onEdit = {}
                                )
                            }
                     //   }
                    }
                }

                taskToDelete?.let {
                    BasicAlertDialog(
                        onDismissRequest = { taskToDelete = null }
                    ) {
                        Surface(
                            shape = MaterialTheme.shapes.large,
                            tonalElevation = AlertDialogDefaults.TonalElevation
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(
                                    text = "Are you sure you want to delete the task with the following details?\n" +
                                            "\nTitle: ${taskToDelete?.title}" +
                                            "\nDescription: ${taskToDelete?.description}"
                                )
                                Spacer(modifier = Modifier.height(24.dp))
                                Row(modifier = Modifier.fillMaxWidth(0.9f),
                                    horizontalArrangement = Arrangement.SpaceBetween) {
                                    TextButton(
                                        onClick = {
                                            taskToDelete = null
                                        }
                                    ) {
                                        Text("Cancel")
                                    }

                                    TextButton(
                                        onClick = {
                                            taskViewModel.deleteTask(taskToDelete!!)
                                            taskToDelete = null
                                        }
                                    ) {
                                        Text("Confirm", color = Color.Red)
                                    }
                                }
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

            }
        }
    }}