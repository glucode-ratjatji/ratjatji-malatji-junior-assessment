package com.example.glucode_getitdone_to_do_list.weather.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
@Preview
fun WeatherCardSkeleton() {
    Card(
        modifier = Modifier.fillMaxWidth(0.9f),
        colors = CardDefaults.cardColors(
            containerColor = Color.LightGray.copy(alpha = 0.3f)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            // Top Section Skeleton
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(4.dp)
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Fake Temp
                    Box(modifier = Modifier.height(32.dp).fillMaxWidth(0.4f).clip(RoundedCornerShape(4.dp)).shimmerEffect())
                    // Fake City
                    Box(modifier = Modifier.height(24.dp).fillMaxWidth(0.6f).clip(RoundedCornerShape(4.dp)).shimmerEffect())
                    // Fake Condition
                    Box(modifier = Modifier.height(20.dp).fillMaxWidth(0.3f).clip(RoundedCornerShape(4.dp)).shimmerEffect())
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Bottom Section Skeleton (Sunrise/Sunset)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                   // .padding(top = 12.dp, bottom = 8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(modifier = Modifier.height(20.dp).fillMaxWidth(0.2f).clip(RoundedCornerShape(4.dp)).shimmerEffect())
                    Box(modifier = Modifier.height(20.dp).fillMaxWidth(0.2f).clip(RoundedCornerShape(4.dp)).shimmerEffect())
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(modifier = Modifier.height(20.dp).fillMaxWidth(0.2f).clip(RoundedCornerShape(4.dp)).shimmerEffect())
                    Box(modifier = Modifier.height(20.dp).fillMaxWidth(0.2f).clip(RoundedCornerShape(4.dp)).shimmerEffect())
                }
            }
        }
    }
}