package com.example.glucode_getitdone_to_do_list.tasks.data.ytapi

data class WeatherResponse(
    val location: Location,
    val current: Current,
    val forecast: Forecast,
)

data class Location(
    val name: String,
    val region: String,
    val country: String,
)

data class Current(
    val temp_c: Double
)

data class Condition(
    val text: String,
    val icon: String,
    val code: Int
)

data class Forecast(
    val forecastday: List<ForecastDay> )

data class ForecastDay(
    val astro: Astronomy
)
data class Astronomy(
    val sunrise: String,
    val sunset: String
)
