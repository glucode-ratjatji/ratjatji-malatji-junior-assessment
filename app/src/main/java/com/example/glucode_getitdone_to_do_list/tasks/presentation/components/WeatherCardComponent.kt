package com.example.glucode_getitdone_to_do_list.tasks.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AcUnit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.glucode_getitdone_to_do_list.tasks.data.ytapi.WeatherResponse


@Composable
fun WeatherCardComponent(
    weatherResponse: WeatherResponse?,
    isLoading: Boolean,
    error: String?
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(0.9f),
        // ✅ Correct way to set a Card's background color
        colors = CardDefaults.cardColors(
            containerColor = Color.Cyan.copy(alpha = 0.8f)
        )
    ) {
        // We use a Column inside the card to stack the top Row and bottom Rows vertically
        Column(modifier = Modifier.padding(12.dp)) {
// Handle Loading State
            if (isLoading) {
                Text("Fetching weather...")
                return@Column
            }

            // Handle Error State
            if (error != null) {
                Text("Error: $error", color = Color.Red)
                return@Column
            }

            weatherResponse?.let { data ->
                // Top section: Temp/City and Icon
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier
                            .padding(4.dp)
                            .weight(1f)
                    ) {
                        Text("${data.current.temp_c}°C", style = MaterialTheme.typography.titleLarge)
                        Text(
                            data.location.name,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleMedium
                        )
                    }

                    // This column now has room to render correctly
                    Column(
                        modifier = Modifier.padding(4.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.AcUnit,
                            contentDescription = "Weather Icon", modifier = Modifier.size(50.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
                val todayForecast = data.forecast.forecastday.firstOrNull()
                val sunriseTime = todayForecast?.astro?.sunrise ?: "--:--"
                val sunsetTime = todayForecast?.astro?.sunset ?: "--:--"
                // Bottom section: Sunrise & Sunset details
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Sunrise", style = MaterialTheme.typography.bodyLarge)
                            Text(sunriseTime, style = MaterialTheme.typography.bodyLarge)
                        }
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
