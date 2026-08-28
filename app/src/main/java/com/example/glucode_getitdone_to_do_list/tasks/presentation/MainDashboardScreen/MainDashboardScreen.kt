package com.example.glucode_getitdone_to_do_list.tasks.presentation.MainDashboardScreen

import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.glucode_getitdone_to_do_list.location.LocationService
import com.example.glucode_getitdone_to_do_list.tasks.data.TaskViewModel.TaskViewModel
import com.example.glucode_getitdone_to_do_list.tasks.data.local.Task
import com.example.glucode_getitdone_to_do_list.tasks.presentation.components.BottomSheet
import com.example.glucode_getitdone_to_do_list.tasks.presentation.components.FABContent
import com.example.glucode_getitdone_to_do_list.tasks.presentation.components.ToDoTab
import com.example.glucode_getitdone_to_do_list.weather.presentation.components.WeatherCardComponent
import com.example.glucode_getitdone_to_do_list.weather.domain.model.WeatherResponse
import com.example.glucode_getitdone_to_do_list.weather.presentation.WeatherViewModel.WeatherViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainDashboardScreen(navController: NavController,
                        viewModel: WeatherViewModel,
                        taskViewModel: TaskViewModel = hiltViewModel()
) {
    val taskList by taskViewModel.visibleTasks.collectAsState()
    var taskToDelete by remember { mutableStateOf<Task?>(null) }
    var taskToUpdate by remember { mutableStateOf<Task?>(null) }
    val weatherData by viewModel.weatherData.collectAsState<WeatherResponse?>()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()
    var showBottomSheet by remember { mutableStateOf(false) }
    val currentFilter by taskViewModel.currentFilter.collectAsStateWithLifecycle()
    val context = LocalContext.current
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
            Spacer(modifier = Modifier.height(16.dp).background(Color.Black))
            if (showBottomSheet) {
                BottomSheet(
                    taskToEdit = taskToUpdate,
                    onDismiss = { showBottomSheet = false
                    taskToUpdate = null}
                )
            }

            Button(onClick = {
                Intent(context, LocationService::class.java).apply {
                    action = LocationService.ACTION_START
                    context.startService(this)
                }
            }) {
                Text(text = "start")
            }

            Button(onClick = {
                Intent(context, LocationService::class.java).apply {
                    action = LocationService.ACTION_STOP
                    context.startService(this)
                }
            }) {
                Text(text = "stop")
            }
            ToDoTab(taskViewModel,
                onTaskTap = {
                    clickedTask ->
                taskToUpdate = clickedTask
                showBottomSheet = true  },

                onTaskLongClick = {
                    heldTask ->
                taskToDelete = heldTask
                                  },

                onCheckedChanged = {
//                    newIsCheckedStatus ->
//                    val toggledTask = task.copy(isComplete = newIsCheckedStatus)
//                    taskViewModel.onTaskUpdated(toggledTask)
                }
            )

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
    }
