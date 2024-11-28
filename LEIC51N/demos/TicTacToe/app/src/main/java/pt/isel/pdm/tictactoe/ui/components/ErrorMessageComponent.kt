package pt.isel.pdm.tictactoe.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import pt.isel.pdm.tictactoe.R


@Composable
fun ErrorMessageComponent(
    error: Throwable,
    onDismiss: () -> Unit,
    onRetry: (() -> Unit)?
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red.copy(alpha = 0.5f))
    )
    {
        Text(
            text = error.toString(),
            modifier = Modifier.align(Alignment.Center)
        )
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = onDismiss,
                modifier = Modifier.padding(12.dp)
            ) {
                Text(text = stringResource(id = R.string.dismiss))
            }

            if (onRetry != null)
                Button(
                    onClick = onRetry,
                    modifier = Modifier.padding(12.dp)
                ) {
                    Text(text = stringResource(id = R.string.retry))
                }
        }

    }
}