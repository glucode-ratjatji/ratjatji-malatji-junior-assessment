package com.example.glucode_getitdone_to_do_list.tasks.presentation.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.glucode_getitdone_to_do_list.tasks.data.ytapi.WeatherResponse
import com.example.glucode_getitdone_to_do_list.R

@Composable
fun WeatherCardComponent(
    weatherResponse: WeatherResponse?,
    isLoading: Boolean,
    error: String?
) {
    weatherResponse?.let { data ->
        Card(
            modifier = Modifier.fillMaxWidth(0.9f),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            //Box to enable layering
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                // TODO: Make this dynamic based on data.current.condition.code
                Image(
                    painter = painterResource(id = R.drawable.overcast_condition),
                    contentDescription = "Weather Background",
                    contentScale = ContentScale.Crop, // Crop ensures the image completely fills the card bounds
                    modifier = Modifier.matchParentSize() // Matches the size of the parent Box
                )
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Top Section
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                    ) {
                        if (isLoading) {
                            Text("Fetching weather...") // TODO Add a skeleton card
                            return@Column
                        }
                        if (error != null) {
                            Text("Error: $error", color = Color.Red)
                            return@Column
                        }
                        // Top section: Temp, City & condition
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp),
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(4.dp)
                                    .weight(1f)
                            ) {
                                Text(
                                    text = "${data.current.temp_c}°C",
                                    style = MaterialTheme.typography.titleLarge,
                                    color = Color.White
                                )
                                Text(
                                    text = data.location.name,
                                    fontWeight = FontWeight.Bold,
                                    style = MaterialTheme.typography.titleMedium,
                                    color = Color.White
                                )
                                Text(
                                    text = data.current.temp_c.toString(),
                                    color = Color.White// TODO:  change to condition
                                )
                            }
                        }
                    }

                    // Bottom section: Sunrise & Sunset details
                    val todayForecast = data.forecast.forecastday.firstOrNull()
                    val sunriseTime = todayForecast?.astro?.sunrise ?: "--:--"
                    val sunsetTime = todayForecast?.astro?.sunset ?: "--:--"

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding( top =12.dp, start = 12.dp, end = 12.dp, bottom = 8.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth()
                                ,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Sunrise", style = MaterialTheme.typography.bodyLarge)
                            Text(sunriseTime, style = MaterialTheme.typography.bodyLarge)
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Sunset", style = MaterialTheme.typography.bodyLarge)
                            Text(sunsetTime, style = MaterialTheme.typography.bodyLarge)
                        }
                    }
                }
            }
        }
    }
}