package pdm.demos.crowdtally

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pdm.demos.crowdtally.ui.theme.CrowdTallyTheme

const val CONFIGURE_VIEW_TAG = "configure_view"
const val SAVE_BUTTON_TAG = "save_button"
const val CANCEL_BUTTON_TAG = "cancel_button"
const val CAPACITY_TEXT_FIELD_TAG = "capacity_text_field"

@Composable
fun ConfigurationView(
    state: CrowdTallyScreenState.Configuration,
    modifier: Modifier = Modifier,
    onSaveIntent: (Int) -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize().padding(16.dp).testTag(CONFIGURE_VIEW_TAG),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var currentCapacity by remember { mutableIntStateOf(state.initialCapacity) }
        TextField(
            value = "$currentCapacity",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = { currentCapacity = it.toIntOrNull() ?: 0 },
            singleLine = true,
            modifier = Modifier.testTag(CAPACITY_TEXT_FIELD_TAG)
        )
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { onSaveIntent(currentCapacity) },
                modifier = Modifier.testTag(SAVE_BUTTON_TAG)
            ) {
                Text(text = stringResource(R.string.save_button_text))
            }
            Spacer(modifier = Modifier.padding(horizontal = 8.dp))
            Button(
                onClick = { onSaveIntent(state.initialCapacity) },
                modifier = Modifier.testTag(CANCEL_BUTTON_TAG)
            ) {
                Text(text = stringResource(R.string.cancel_button_text))
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ConfigurationViewPreview() {
    CrowdTallyTheme {
        ConfigurationView(
            state = CrowdTallyScreenState.Configuration(initialCapacity = 1000),
            onSaveIntent = {}
        )
    }
}