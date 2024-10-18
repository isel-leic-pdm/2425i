package pt.isel.pdm.nasaimageoftheday.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import pt.isel.pdm.nasaimageoftheday.R


@Composable
fun GenericErrorView(
    error: Throwable,
    onDismissRequested: () -> Unit,
    modifier: Modifier = Modifier

) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ErrorAlertImpl(
            title = stringResource(R.string.generic_error),
            message = stringResource(R.string.generic_error_message) + error.toString(),
            buttonText = stringResource(R.string.generic_error_button),
            onDismiss = onDismissRequested
        )
    }
}

@Composable
fun ErrorAlert(
    @StringRes title: Int,
    @StringRes message: Int,
    @StringRes buttonText: Int,
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit = { }
) {
    ErrorAlertImpl(
        title = stringResource(id = title),
        message = stringResource(id = message),
        buttonText = stringResource(id = buttonText),
        onDismiss = onDismiss,
        modifier
    )
}

@Composable
private fun ErrorAlertImpl(
    title: String,
    message: String,
    buttonText: String,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = { },
        confirmButton = {
            OutlinedButton(
                border = BorderStroke(0.dp, Color.Unspecified),
                onClick = onDismiss,
            ) {
                Text(text = buttonText)
            }
        },
        title = { Text(text = title) },
        text = { Text(text = message) },
        icon = {
            Icon(
                imageVector = Icons.Default.Warning,
                contentDescription = "Warning"
            )
        },
    )
}