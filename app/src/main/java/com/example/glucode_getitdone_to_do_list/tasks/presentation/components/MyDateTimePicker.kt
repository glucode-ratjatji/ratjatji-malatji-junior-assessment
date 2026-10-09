package com.example.glucode_getitdone_to_do_list.tasks.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import java.util.Calendar

enum class PickerStep {
    DATE, TIME
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateTimePickerDialog(
    onDateTimeSelected: (Long?) -> Unit, // Returns combined timestamp in milliseconds
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState()

    val currentTime = Calendar.getInstance()
    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = true
    )

    // Track whether we are currently showing the DatePicker or TimePicker
    var currentStep by remember { mutableStateOf(PickerStep.DATE) }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = {
                    when (currentStep) {
                        PickerStep.DATE -> {
                            // Proceed to time picker if a date was chosen
                            if (datePickerState.selectedDateMillis != null) {
                                currentStep = PickerStep.TIME
                            }
                        }
                        PickerStep.TIME -> {
                            // Combine Date and Time into a single timestamp
                            val dateMillis = datePickerState.selectedDateMillis
                            if (dateMillis != null) {
                                val calendar = Calendar.getInstance().apply {
                                    timeInMillis = dateMillis
                                    set(Calendar.HOUR_OF_DAY, timePickerState.hour)
                                    set(Calendar.MINUTE, timePickerState.minute)
                                    set(Calendar.SECOND, 0)
                                    set(Calendar.MILLISECOND, 0)
                                }
                                onDateTimeSelected(calendar.timeInMillis)
                            }
                            onDismiss()
                        }
                    }
                }
            ) {
                Text(if (currentStep == PickerStep.DATE) "Next" else "OK")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    if (currentStep == PickerStep.TIME) {
                        // Allow going back to the date picker
                        currentStep = PickerStep.DATE
                    } else {
                        onDismiss()
                    }
                }
            ) {
               Text(if (currentStep == PickerStep.DATE) "Cancel" else "Back") // Simplified below
            }
        },
        text = {
            when (currentStep) {
                PickerStep.DATE -> {
                    DatePicker(state = datePickerState)
                }
                PickerStep.TIME -> {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        TimePicker(state = timePickerState)
                    }
                }
            }
        }
    )
}

// Helper to keep cancel/back button text clean
@Composable
private fun getDismissButtonText(step: PickerStep): String {
    return if (step == PickerStep.DATE) "Cancel" else "Back"
}