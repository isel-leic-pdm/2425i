package pt.isel.pdm.tictactoe.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import pt.isel.pdm.tictactoe.R

@Composable
fun AppScaffold(
    @StringRes title: Int? = null,
    navigationHandlers: NavigationHandlers? = null,
    floatingActionButton: (@Composable () -> Unit)? = null,
    content: @Composable (PaddingValues) -> Unit
) {

    Scaffold(
        topBar = {
            TopBar(
                title = title ?: R.string.app_name,
                navigationHandlers = navigationHandlers ?: NavigationHandlers()
            )
        },
        floatingActionButton = {
            if (floatingActionButton != null)
                floatingActionButton()
        }
    ) {
        content(it)
    }
}