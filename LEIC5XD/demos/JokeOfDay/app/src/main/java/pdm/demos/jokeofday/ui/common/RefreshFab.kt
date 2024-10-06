package pdm.demos.jokeofday.ui.common

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pdm.demos.jokeofday.ui.theme.JokeOfDayIcons

@Composable
fun RefreshFab(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    refreshing: Boolean = false
) {
    Button(
        onClick = onClick,
        enabled = refreshing.not(),
        shape = CircleShape,
        modifier = modifier
            .defaultMinSize(minWidth = 72.dp, minHeight = 72.dp)
    ) {
        if (refreshing) {
            val transition = rememberInfiniteTransition(label = "fab rotation")
            val rotation by transition.animateFloat(
                initialValue = 0f,
                targetValue = 360f,
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        delayMillis = 50,
                        durationMillis = 750,
                        easing = FastOutSlowInEasing
                    )
                ), label = ""
            )
            Icon(
                JokeOfDayIcons.Refresh,
                contentDescription = "Refresh",
                modifier = Modifier.rotate(rotation)
            )
        } else {
            Icon(
                JokeOfDayIcons.Refresh,
                contentDescription = "Refresh",
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun IdleRefreshFabPreview() {
    RefreshFab(onClick = { }, refreshing = false)
}

@Preview(showBackground = true)
@Composable
private fun RefreshingRefreshFabPreview() {
    RefreshFab(onClick = { }, refreshing = true)
}
