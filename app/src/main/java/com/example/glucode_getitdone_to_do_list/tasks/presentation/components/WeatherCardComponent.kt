package com.example.glucode_getitdone_to_do_list.tasks.presentation.components

import ads_mobile_sdk.h6
import android.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AcUnit
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.res.painterResource
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
//        colors = CardDefaults.cardColors(
//        containerColor = gradientBrush
//
//           // Color.LightGray.copy(alpha = 0.8f)
//        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {

            Column(modifier = Modifier
                .background(brush = Brush.linearGradient(
                colors = listOf(Color.White, Color.Yellow),
                start = Offset(0f, 0f),
                end = Offset.Infinite))
                    .padding(12.dp)
            ) {
                if (isLoading) {
                    Text("Fetching weather...") //TO DO Add a skeleton card
                    return@Column
                }
                if (error != null) {
                    Text("Error: $error", color = Color.Red)
                    return@Column
                }

                weatherResponse?.let { data ->
                // Top section: Temp/City and Icon
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .height(100.dp),
                    //verticalAlignment = Alignment.CenterVertically
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
                        Text(
                            data.current.temp_c.toString()
                        )
                    }
                }
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
                        imageVector = Icons.Default.WbSunny,
                        contentDescription = "Weather Icon"
                        , modifier = Modifier.size(50.dp)
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
