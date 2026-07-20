package com.example.trainerappicationgooglespeech.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.glucode_getitdone_to_do_list.tasks.data.ytWeatherViewModel.WeatherViewModel
import com.example.glucode_getitdone_to_do_list.tasks.data.ytapi.RetroFitInstance
import com.example.glucode_getitdone_to_do_list.tasks.data.ytrepository.WeatherRepository
import com.example.glucode_getitdone_to_do_list.tasks.presentation.MainDashboardScreen.MainDashboardScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainNavGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = ToDoWeatherScreens.HomeScreen.name
    ) {
        // App start up
//        composable(TrainerScreens.SplashScreen.name) {
//            TrainerSplashScreen(navController = navController)
//        }


        composable(ToDoWeatherScreens.HomeScreen.name) {
            val apiService = RetroFitInstance.apiService
            val repository = WeatherRepository(apiService)

            // 2. Create the ViewModel using a Factory
            val viewModel: WeatherViewModel = viewModel(
                factory = object : ViewModelProvider.Factory {
                    @Suppress("UNCHECKED_CAST")
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        return WeatherViewModel(repository) as T
                    }
                }
            )
            MainDashboardScreen(viewModel = viewModel, navController = navController )
        }

    }
}