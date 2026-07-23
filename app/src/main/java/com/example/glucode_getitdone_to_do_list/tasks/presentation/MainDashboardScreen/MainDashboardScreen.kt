package com.example.glucode_getitdone_to_do_list.tasks.presentation.MainDashboardScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.glucode_getitdone_to_do_list.tasks.data.TaskViewModel.TaskViewModel
import com.example.glucode_getitdone_to_do_list.weather.presentation.WeatherViewModel.WeatherViewModel
import com.example.glucode_getitdone_to_do_list.weather.domain.model.WeatherResponse
import com.example.glucode_getitdone_to_do_list.tasks.presentation.components.BottomSheet
import com.example.glucode_getitdone_to_do_list.tasks.presentation.components.FABContent
import com.example.glucode_getitdone_to_do_list.weather.presentation.components.WeatherCardComponent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainDashboardScreen(navController: NavController,
                        viewModel: WeatherViewModel,
taskViewModel: TaskViewModel = hiltViewModel()
) {
    val taskList by taskViewModel.tasks.collectAsState()
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
        }
    ) { innerPadding ->


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            WeatherCardComponent(
                weatherResponse = weatherData,
                isLoading = isLoading,
                error = error
            )

            if (showBottomSheet) {
                BottomSheet(
                    taskViewModel,
                    onDismiss = { showBottomSheet = false }
                )
            }

            LazyColumn(
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            ) {
                // This loop goes through every task in your Room database
                items(taskList) { task ->
                    // You can create a custom 'TaskItemCard' component later,
                    // but for now, we'll just print the text to prove it works!
                    Column(modifier = Modifier.padding(vertical = 8.dp)) {
                        Text(text = "Title: ${task.title} ${task.id}", fontWeight = FontWeight.Bold)
                        Text(text = "Description: ${task.description}")
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            // 2. Display the tasks using a LazyColumn
            Text(text = "My Tasks", style = MaterialTheme.typography.titleLarge)

        }
    }
}