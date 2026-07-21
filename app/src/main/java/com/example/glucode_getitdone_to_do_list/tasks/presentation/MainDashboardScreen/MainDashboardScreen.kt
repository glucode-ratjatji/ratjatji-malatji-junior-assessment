package com.example.glucode_getitdone_to_do_list.tasks.presentation.MainDashboardScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.glucode_getitdone_to_do_list.tasks.data.TaskViewModel.TaskViewModel
import com.example.glucode_getitdone_to_do_list.tasks.data.ytWeatherViewModel.WeatherViewModel
import com.example.glucode_getitdone_to_do_list.tasks.data.ytapi.WeatherResponse
import com.example.glucode_getitdone_to_do_list.tasks.presentation.components.EnterToDoDetails
import com.example.glucode_getitdone_to_do_list.tasks.presentation.components.WeatherCardComponent

@Composable
fun MainDashboardScreen(navController: NavController,
                        viewModel: WeatherViewModel,
taskViewModel: TaskViewModel = hiltViewModel()
) {
    val taskList by taskViewModel.tasks.collectAsState()
    val weatherData by viewModel.weatherData.collectAsState<WeatherResponse?>()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    // 2. Trigger the fetch instantly on launch
    LaunchedEffect(Unit) {
        viewModel.fetchWeather("Sandton")
    }

    // 3. Pass the state down to your component
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(16.dp))

        WeatherCardComponent(
            weatherResponse = weatherData,
            isLoading = isLoading,
            error = error
        )
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
        EnterToDoDetails(
            viewModel = taskViewModel
        )
        Spacer(modifier = Modifier.height(16.dp))

        // 2. Display the tasks using a LazyColumn
        Text(text = "My Tasks", style = MaterialTheme.typography.titleLarge)



    }
}
