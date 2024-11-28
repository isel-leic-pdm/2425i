package pt.isel.pdm.tictactoe.ui.components

import androidx.compose.foundation.Indication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role

@Composable
fun Modifier.preventBehindElementsFromBeingClicked(): Modifier {
    return this.clickable(
        indication = null, // disable ripple effect
        interactionSource = remember { MutableInteractionSource() },
        onClick = { })

}