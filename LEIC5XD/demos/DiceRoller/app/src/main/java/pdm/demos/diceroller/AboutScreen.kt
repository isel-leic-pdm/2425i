package pdm.demos.diceroller

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import pdm.demos.diceroller.ui.theme.DiceRollerTheme

@Composable
fun AboutScreen(onNavigateBack: () -> Unit = {}) {
    Column(modifier = Modifier.fillMaxSize()) {
        Button(onClick = { onNavigateBack() }) {
            Text(text = "Back")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AboutScreenPreview() {
    DiceRollerTheme {
        AboutScreen()
    }
}