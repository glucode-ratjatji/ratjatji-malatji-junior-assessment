package com.example.glucode_getitdone_to_do_list.tasks.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.glucode_getitdone_to_do_list.tasks.data.TaskViewModel.TaskViewModel
import com.example.glucode_getitdone_to_do_list.tasks.data.local.Task

@OptIn(ExperimentalMaterial3Api::class)
@Composable
//https://developer.android.com/develop/ui/compose/components/bottom-sheets-partial
fun BottomSheet(taskToEdit: Task? = null, taskViewModel: TaskViewModel = hiltViewModel(), onDismiss: () -> Unit) {
    var showBottomSheet by remember { mutableStateOf(true) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
    )

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (showBottomSheet) {
            ModalBottomSheet(

                sheetState = sheetState,
                onDismissRequest = onDismiss
            ) {
                EnterToDoDetails(
                    dismissBottomSheet = onDismiss,
                    taskToEdit = taskToEdit,
                    viewModel = taskViewModel
                )
        }
    }
}
}