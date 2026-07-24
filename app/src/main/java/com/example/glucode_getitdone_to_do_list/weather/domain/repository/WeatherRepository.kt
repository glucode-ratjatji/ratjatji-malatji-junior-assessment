package com.example.glucode_getitdone_to_do_list.weather.domain.repository

import com.example.glucode_getitdone_to_do_list.BuildConfig
import com.example.glucode_getitdone_to_do_list.weather.api.WeatherApiService
import com.example.glucode_getitdone_to_do_list.weather.domain.model.WeatherResponse
import retrofit2.Response
import com.example.glucode_getitdone_to_do_list.weather.utils.Result
class WeatherRepository(private val apiService: WeatherApiService) {

    suspend fun getWeatherData(location: String): Result<WeatherResponse>{
        return try{
val response: Response<WeatherResponse> = apiService.getCurrentWeather(
    apiKey = BuildConfig.MY_API_KEY,
    location = location)
            if(response.isSuccessful){
                val weatherResponse = response.body()
                if( weatherResponse != null){
                    Result.Success(weatherResponse)
                }else{
                    Result.Error(Exception("Response body is null"))
                }
            }
            else{
                Result.Error(Exception("Response failed with code: ${response.code()}"))
            }
        }catch(e: Exception){
            Result.Error(e)
        }
    }
}