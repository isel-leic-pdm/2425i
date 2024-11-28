package pt.isel.pdm.tictactoe.ui.components

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import pt.isel.pdm.tictactoe.R


data class NavigationHandlers(
    val onBackHandler: (() -> Unit)? = null,
    val refreshHandler: (() -> Unit)? = null,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    @StringRes title: Int = R.string.app_name,
    navigationHandlers: NavigationHandlers = NavigationHandlers()
) {
    TopAppBar(
        title = { Text(stringResource(id = title)) },
        navigationIcon = {
            ConditionalIconButton(
                action = navigationHandlers.onBackHandler,
                image = Icons.Default.ArrowBack,
                string = R.string.top_bar_go_back
            )
        },
        actions = {
            ConditionalIconButton(
                action = navigationHandlers.refreshHandler,
                image = Icons.Default.Refresh,
                string = R.string.refresh
            )
        }

    )
}


@Composable
fun ConditionalIconButton(
    action: (() -> Unit)?,
    image: ImageVector,
    @StringRes string: Int,
    modifier: Modifier = Modifier
) {
    if (action != null) {
        IconButton(
            onClick = action,
            modifier = modifier
        ) {
            Icon(
                imageVector = image,
                contentDescription = stringResource(id = string)
            )
        }
    }
}

@Preview
@Composable
private fun TopBarPreviewInfoAndHistory() {
    TopBar(
        navigationHandlers = NavigationHandlers()
    )
}

@Preview
@Composable
private fun TopBarPreviewBackAndInfo() {
    TopBar(navigationHandlers = NavigationHandlers(onBackHandler = { }))
}

@Preview
@Composable
private fun TopBarPreviewBack() {
    TopBar(navigationHandlers = NavigationHandlers(onBackHandler = { }))
}