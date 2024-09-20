package pdm.demos.crowdtally

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RoundButton(
    onClick: () -> Unit,
    symbol: Char,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .padding(8.dp)
            .size(60.dp)
            .clip(CircleShape),
        shape = CircleShape
    ) {
        Text(text = symbol.toString(), fontSize = 24.sp)
    }
}

@Preview
@Composable
fun RoundButtonPreview() {
    RoundButton(onClick = {}, symbol = '+')
}
