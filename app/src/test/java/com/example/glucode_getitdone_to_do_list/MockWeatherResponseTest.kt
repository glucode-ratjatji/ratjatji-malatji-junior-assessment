package com.example.glucode_getitdone_to_do_list

import com.example.glucode_getitdone_to_do_list.weather.domain.model.Astronomy
import com.example.glucode_getitdone_to_do_list.weather.domain.model.Condition
import com.example.glucode_getitdone_to_do_list.weather.domain.model.Current
import com.example.glucode_getitdone_to_do_list.weather.domain.model.Forecast
import com.example.glucode_getitdone_to_do_list.weather.domain.model.ForecastDay
import com.example.glucode_getitdone_to_do_list.weather.domain.model.Location
import com.example.glucode_getitdone_to_do_list.weather.domain.model.WeatherResponse

fun getMockWeatherResponse(): WeatherResponse {
    return WeatherResponse(
        location = Location(name = "Pretoria", region = "Gauteng", country = "South Africa"),
        current = Current(temp_c = 25.0, condition = Condition("Sunny", "icon_url", 1000)),
        forecast = Forecast(
            forecastday = listOf(
                ForecastDay(astro = Astronomy("06:00 AM", "06:30 PM"))
            )
        )
    )
}