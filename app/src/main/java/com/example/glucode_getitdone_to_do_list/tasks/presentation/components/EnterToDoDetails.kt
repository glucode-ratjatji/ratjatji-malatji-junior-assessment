package com.example.glucode_getitdone_to_do_list.tasks.presentation.components

import android.annotation.SuppressLint
import android.view.Surface
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NoteAlt
import androidx.compose.material3.Button
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
import androidx.lifecycle.viewmodel.compose.viewModel

@SuppressLint("UnrememberedMutableState", "UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun EnterToDoDetails() {
    val focusManager = LocalFocusManager.current
    var title by remember{mutableStateOf("fdgdfggdgdfg")}
    var description by remember{mutableStateOf("ddfgdfgdgdgfdgfdgdgdgdgdgdfggfdgdgdgdfgdfgdgdgdgdgdfgfdgdgdgdggfdfgd")}

    Scaffold(

    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 40.dp)

        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .background(Color.White)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedTextField(
                    value = title,
                    onValueChange = {title = it},
                    label = { Text("Enter to do title") },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Next) }
                    )
                )
                OutlinedTextField(
                    value = description,
                    onValueChange = {description = it},
                    label = { Text("Enter to do description") },
                    minLines = 2,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(
                        onDone = { focusManager.clearFocus() }
                    )
                )
                Button(onClick = {}) {
                    Text("Add item")
                }
            }
        }
    }
}


@Composable
fun FormItem(title : String, content: @Composable ColumnScope.() -> Unit)
{
    Column()
    {
        Row(modifier = Modifier.fillMaxWidth())
        {
            Text(text = title, style = MaterialTheme.typography.titleSmall)
            Spacer(modifier = Modifier.height(5.dp))

        }
        Spacer(modifier = Modifier.height(5.dp))
        content()
        Spacer(modifier = Modifier.height(5.dp))
    }
}

@Composable
fun FormTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier, // Used for the outer column
    textFieldModifier: Modifier = Modifier, // NEW: Used for the actual input box
    placeholder: String = "",
    isError: Boolean = false,
    errorMessage: String = "",
    keyboardType: KeyboardType = KeyboardType.Text,
    singleLine: Boolean = true,
    enabled: Boolean = true,
    leadingIcon: ImageVector? = null
) {
    var isFocused by remember { mutableStateOf(false) }

    val defaultBorder = Brush.horizontalGradient(
        colors = listOf(
            Color.LightGray,
            Color.Black
        )
    )

    Column(
        modifier = modifier.fillMaxWidth() // Outer layout
    ) {
        Box(
            modifier = textFieldModifier // <--- APPLY THE NEW MODIFIER HERE
                .fillMaxWidth()
                .border(
                    width = 2.dp,
                    brush =  defaultBorder, // Assuming Kolab is your custom brush
                    shape = RoundedCornerShape(12.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            OutlinedTextField(
                // ... all your existing OutlinedTextField code remains exactly the same
                value = value,
                onValueChange = onValueChange,
                label = { Text(label) },
                placeholder = {
                    if (placeholder.isNotEmpty()) {
                        Text(placeholder)
                    }
                },
                singleLine = singleLine,
                isError = isError,
                enabled = enabled,
                keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
                leadingIcon = {
                    leadingIcon?.let {
                        Icon(imageVector = it, contentDescription = null)
                    }
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    errorBorderColor = Color.Transparent
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged { focusState ->
                        isFocused = focusState.isFocused
                    }
            )
        }

        if (isError && errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = Color.Red,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}