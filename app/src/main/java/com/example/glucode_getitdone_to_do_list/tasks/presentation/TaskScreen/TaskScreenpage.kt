package com.example.glucode_getitdone_to_do_list.tasks.presentation.TaskScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.glucode_getitdone_to_do_list.tasks.presentation.components.CheckableCardComponent
import com.example.glucode_getitdone_to_do_list.tasks.presentation.components.ToDoTab
import com.example.glucode_getitdone_to_do_list.tasks.presentation.components.WeatherCardComponent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TaskScreenPage() {
    Scaffold(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier= Modifier.height(100.dp))
            WeatherCardComponent()
            ToDoTab()
            CheckableCardComponent("Add a top App Bar", "This will show the name of the application")
            CheckableCardComponent("create a card for the weather", "This will display the current weather, sunrise, sunset with an emoji")
            CheckableCardComponent()
        }
    }
}