import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ContainerDelete() {
    // 1. Your list state
    val itemsList = remember { mutableStateListOf("Item 1", "Item 2", "Item 3", "Item 4") }

    // 2. Track WHICH item is queued for deletion. Null means no dialog.
    var itemToDelete by remember { mutableStateOf<String?>(null) }

    // The main list UI
    LazyColumn {
        items(itemsList, key = { it }) { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .combinedClickable(
                        onClick = {
                            // Handle normal click here
                        },
                        onLongClick = {
                            // 3. Trigger the dialog by assigning the selected item
                            itemToDelete = item
                        }
                    )
            ) {
                Text(
                    text = item,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }

    // 4. Show the dialog only if an item is selected
    if (itemToDelete != null) {
        BasicAlertDialog(
            onDismissRequest = {
                // Dismiss the dialog without deleting
                itemToDelete = null
            }
        ) {
            Surface(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight(),
                shape = MaterialTheme.shapes.large,
                tonalElevation = AlertDialogDefaults.TonalElevation,
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Are you sure you want to delete $itemToDelete?"
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    TextButton(
                        onClick = {
                            // 5. Remove the specific item and close the dialog
                            itemsList.remove(itemToDelete)
                            itemToDelete = null
                        },
                        modifier = Modifier.align(Alignment.End),
                    ) {
                        Text("Confirm")
                    }
                }
            }
        }
    }
}