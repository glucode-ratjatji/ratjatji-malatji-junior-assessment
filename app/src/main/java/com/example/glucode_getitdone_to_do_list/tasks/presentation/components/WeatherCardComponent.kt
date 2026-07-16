package com.example.glucode_getitdone_to_do_list.tasks.presentation.components

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AcUnit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun WeatherCardComponent() {
    Card(
        modifier = Modifier
            .fillMaxWidth(0.9f),
        // ✅ Correct way to set a Card's background color
        colors = androidx.compose.material3.CardDefaults.cardColors(
            containerColor = Color.Cyan.copy(alpha = 0.8f)
        )
    ) {
        // We use a Column inside the card to stack the top Row and bottom Rows vertically
        Column(modifier = Modifier.padding(12.dp)) {

            // Top section: Temp/City and Icon
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .padding(4.dp)
                        .weight(1f) // ✅ Changed from fillMaxWidth() to weight(1f)
                ) {
                    Text("36°C", style = MaterialTheme.typography.titleLarge)
                    Text(
                        "Johannesburg",
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
                        contentDescription = "Weather Icon"
                        , modifier = Modifier.size(50.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Bottom section: Sunrise & Sunset details
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Sunrise", style = MaterialTheme.typography.bodyLarge)
                    Text("06:30", style = MaterialTheme.typography.bodyLarge)
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Sunset", style = MaterialTheme.typography.bodyLarge)
                    Text("18:00", style = MaterialTheme.typography.bodyLarge)
                }
            }
        }
    }
}