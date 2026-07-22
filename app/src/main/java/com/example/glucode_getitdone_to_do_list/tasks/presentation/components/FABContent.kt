package com.example.glucode_getitdone_to_do_list.tasks.presentation.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun FABContent(onTap: () -> Unit) {
    FloatingActionButton(
        onClick = {},// onTap() },
        shape = RoundedCornerShape(20.dp),
        containerColor = Color.Blue,
        contentColor = Color.White
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Create a To-Do",
            tint = Color.White
        )
    }
}