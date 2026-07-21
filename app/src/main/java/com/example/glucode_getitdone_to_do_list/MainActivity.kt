package com.example.glucode_getitdone_to_do_list

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckBox
import androidx.compose.material.icons.filled.CheckBoxOutlineBlank
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.glucode_getitdone_to_do_list.tasks.presentation.TaskScreen.TaskScreenPage
import com.example.glucode_getitdone_to_do_list.tasks.presentation.components.BottomSheet
import com.example.glucode_getitdone_to_do_list.tasks.presentation.components.CheckableCardComponent
import com.example.glucode_getitdone_to_do_list.tasks.presentation.components.EnterToDoDetails
import com.example.glucode_getitdone_to_do_list.tasks.presentation.components.ToDoTab
import com.example.glucode_getitdone_to_do_list.tasks.presentation.components.WeatherCardComponent
import com.example.glucode_getitdone_to_do_list.ui.theme.Glucode_GetItDone_To_Do_ListTheme
import okhttp3.internal.concurrent.Task

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Glucode_GetItDone_To_Do_ListTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    Column(modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Spacer(modifier= Modifier.height(100.dp))
//                        WeatherCardComponent()

//                        CheckableCardComponent("Add a top App Bar", "This will show the name of the application")
//                        CheckableCardComponent("create a card for the weather", "This will display the current weather, sunrise, sunset with an emoji")
//                        CheckableCardComponent()
//                        EnterToDoDetails()
                        BottomSheet()

                    }
                }
            }
        }
    }
}
