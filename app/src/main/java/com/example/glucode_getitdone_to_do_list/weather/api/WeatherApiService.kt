package com.example.glucode_getitdone_to_do_list.tasks.data.ytapi

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    //http://api.weatherapi.com/v1/forecast.json?key=764928825f1c4aad86360913261507&q=Sandton&aqi=no

    @GET("forecast.json")
    suspend fun getCurrentWeather(
        @Query("key") apiKey: String,
        @Query("q") location: String,
        @Query("days") days: Int = 1,
        @Query("aqi") airQuality: String = "no",
        @Query("alerts") alerts: String = "no",
    ): Response<WeatherResponse>

}