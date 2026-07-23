package com.example.glucode_getitdone_to_do_list.weather.presentation.WeatherViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.glucode_getitdone_to_do_list.weather.domain.model.WeatherResponse
import com.example.glucode_getitdone_to_do_list.weather.utils.Result
import com.example.glucode_getitdone_to_do_list.weather.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherViewModel(private val repository: WeatherRepository): ViewModel() {

    private val _weatherData = MutableStateFlow<WeatherResponse?>(null)
    val weatherData: StateFlow<WeatherResponse?> = _weatherData

    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun fetchWeather(city: String) {
        viewModelScope.launch {
            _isLoading.value = true
            when (val result = repository.getWeatherData(city)) {
                is Result.Success -> {
                    _weatherData.value = result.data
                    _error.value = null
                }
                is Result.Error -> {
                    _error.value = result.exception.message ?: "An unknown error occurred"
                }
                is Result.Loading -> _isLoading.value = true
            }
            _isLoading.value = false
        }
    }

}