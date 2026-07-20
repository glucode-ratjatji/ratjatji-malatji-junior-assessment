package com.example.glucode_getitdone_to_do_list.tasks.presentation.MainDashboardScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.glucode_getitdone_to_do_list.tasks.data.ytWeatherViewModel.WeatherViewModel
import com.example.glucode_getitdone_to_do_list.tasks.data.ytapi.WeatherResponse
import com.example.glucode_getitdone_to_do_list.tasks.presentation.components.WeatherCardComponent

@Composable
fun MainDashboardScreen(navController: NavController, viewModel: WeatherViewModel) {
    // 1. Collect the state from the ViewModel
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
    }
}