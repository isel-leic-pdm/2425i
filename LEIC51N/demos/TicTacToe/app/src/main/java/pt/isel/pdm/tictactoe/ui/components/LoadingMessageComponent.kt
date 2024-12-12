package pt.isel.pdm.tictactoe.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import pt.isel.pdm.tictactoe.R

@Composable
fun LoadingMessageComponent(
    @StringRes strId: Int = R.string.loading
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background.copy(alpha = .8f))
            .preventBehindElementsFromBeingClicked()

    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = stringResource(id = strId)
        )


    }
}


