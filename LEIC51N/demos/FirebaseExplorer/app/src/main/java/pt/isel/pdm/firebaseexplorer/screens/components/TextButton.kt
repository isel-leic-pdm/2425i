package pt.isel.pdm.firebaseexplorer.screens.components

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun TextButton(
    str: String,
    click: () -> Unit
) {
    Button(onClick = click) {
        Text(str)
    }
}