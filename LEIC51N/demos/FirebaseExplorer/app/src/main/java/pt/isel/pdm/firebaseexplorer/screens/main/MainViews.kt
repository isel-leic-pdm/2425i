package pt.isel.pdm.firebaseexplorer.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pt.isel.pdm.firebaseexplorer.model.SimpleModel

@Composable
fun MessageView(message: String) {
    Text(text = message)
}

@Composable
fun ModelListView(
    data: List<SimpleModel>,
    onUpdate: (SimpleModel) -> Unit,
    onDelete: (SimpleModel) -> Unit,
    onRefresh: () -> Unit
) {
    Button(onClick = onRefresh) {
        Text("Refresh")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        data.forEach { model ->
            Row() {
                IconButton(
                    modifier = Modifier.padding(6.dp),
                    onClick = { onUpdate(model) },
                ) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "")
                }

                IconButton(
                    modifier = Modifier.padding(12.dp),
                    onClick = { onDelete(model) },
                ) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "")
                }

                Text("${model.id} || ${model.number}")
            }

        }
    }
}