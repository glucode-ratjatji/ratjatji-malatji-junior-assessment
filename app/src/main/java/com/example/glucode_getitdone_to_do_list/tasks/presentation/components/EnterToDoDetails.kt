package com.example.glucode_getitdone_to_do_list.tasks.presentation.components

import android.annotation.SuppressLint
import android.view.Surface
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NoteAlt
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.glucode_getitdone_to_do_list.tasks.data.TaskViewModel.TaskViewModel
import com.example.glucode_getitdone_to_do_list.tasks.data.local.Task

@SuppressLint("UnrememberedMutableState", "UnusedMaterial3ScaffoldPaddingParameter",
    "SuspiciousIndentation"
)
@Composable
fun EnterToDoDetails(viewModel: TaskViewModel, taskToEdit: Task? = null, dismissBottomSheet: () -> Unit) {
    val items by viewModel.visibleTasks.collectAsStateWithLifecycle()
    val focusManager = LocalFocusManager.current
    var title by rememberSaveable(taskToEdit){mutableStateOf(taskToEdit?.title?:"")}
    var description by rememberSaveable(taskToEdit){mutableStateOf(taskToEdit?.description?:"")}
    val isButtonEnabled = title.isNotEmpty()

    //New To-Do changes to Edit To-Do
    val isTaskBeingEdited = taskToEdit != null
    val headerText = if (isTaskBeingEdited) "Edit To-Do" else "New To-Do"
    val buttonText = if (isTaskBeingEdited) "Save changes" else "Add item"
           Surface(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize()
                .padding(horizontal = 12.dp)

        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                //Row with cancel, heading and save
                Box(modifier = Modifier.fillMaxWidth()){
                    Text(
                        "Cancel",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier
                            .clickable(onClick = dismissBottomSheet)
                            .align(alignment = Alignment.CenterStart),
                        color = Color.Blue)

                    Text(
                        headerText,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier
                            .align(alignment = Alignment.Center)
                    )
                }
                OutlinedTextField(
                    modifier = Modifier.width(300.dp),
                    value = title,
                    onValueChange = {title = it},
                    label = { Text("Title") },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Next) }
                    )
                )
                OutlinedTextField(
                    modifier = Modifier.width(300.dp),
                    value = description,
                    onValueChange = {description = it},
                    label = { Text("Description") },
                    minLines = 2,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(
                        onDone = { focusManager.clearFocus() }
                    )
                )
                Button(onClick = {
                    if(isTaskBeingEdited){
                        val updatedTask = taskToEdit.copy(
                            title = title,
                            description = description
                        )
                        viewModel.onTaskUpdated(updatedTask)
                    }else{
                    viewModel.addTask(title, description)
                    }
                    //Clear input after creating or updating task details
                    title = ""
                    description=""
                    dismissBottomSheet()
                }, enabled = isButtonEnabled, colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Blue,
                    disabledContentColor = Color.LightGray
                )){

                    Text(buttonText)
                }
            }
        }
    }
