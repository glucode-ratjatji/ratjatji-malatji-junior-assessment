package com.example.glucode_getitdone_to_do_list.tasks.data.ytWeatherViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.glucode_getitdone_to_do_list.tasks.data.ytapi.WeatherResponse
import com.example.glucode_getitdone_to_do_list.tasks.data.ytrepository.WeatherRepository
import kotlinx.coroutines.launch
import com.example.glucode_getitdone_to_do_list.tasks.data.ytutils.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

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