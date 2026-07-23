package com.example.glucode_getitdone_to_do_list.tasks.presentation.components

import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.room.util.TableInfo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckableCardComponent(
    title: String = "This is an example of a title",
    description: String = "A description is usually a lot longer than a title so it is important that when it appears it uses more than 2 lines ",
    isChecked: Boolean = false,
    onLongClick: () -> Unit,
    onCheckedChanged: (Boolean) -> Unit
){
    Card(
        modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .combinedClickable(
                    onClick ={},
                    onLongClick = { onLongClick() }
                ),
            verticalAlignment = Alignment.CenterVertically) {
            Column() {
                Checkbox(
                    // set the state of checkbox.
                    checked = isChecked,
                    onCheckedChange = { onCheckedChanged(it)},
                    modifier = Modifier.padding(8.dp),
                    enabled = true,
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color.Blue,
                        uncheckedColor = Color.DarkGray,
                        checkmarkColor = Color.White
                    ),
                    interactionSource = remember { MutableInteractionSource() }
                )
            }
            Column(modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
            ) {
                Text(title, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
                Text(description, style = MaterialTheme.typography.bodyLarge)
            }
            //To do - maxlines w/ the ability to expand a card for more details
    }
}
}