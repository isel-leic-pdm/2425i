package com.example.diceroll.screens.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.example.diceroll.R

class NavigationActions(
    val onBackAction: (() -> Unit)? = null,
    val onAboutAction: (() -> Unit)? = null,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    title: Int? = null,
    navActions: NavigationActions = NavigationActions()
) {
    TopAppBar(
        title = { Text(stringResource(title ?: R.string.app_name)) },
        navigationIcon = {
            ConditionalIconButton(Icons.AutoMirrored.Filled.ArrowBack, navActions.onBackAction)
        },
        actions = {
            ConditionalIconButton(Icons.Default.Info, navActions.onAboutAction)
        }
    )
}

@Composable
fun ConditionalIconButton(
    icon: ImageVector,
    action: (() -> Unit)?

) {
    if (action != null) {
        IconButton(onClick = action) {
            Icon(imageVector = icon, contentDescription = "")
        }
    }
}