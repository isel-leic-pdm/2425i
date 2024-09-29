package pdm.demos.crowdtally

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

const val COUNTING_VIEW_TAG = "counting_view"
const val COUNTER_VIEW_TAG = "counter"
const val INCREMENT_BUTTON_TAG = "increment"
const val DECREMENT_BUTTON_TAG = "decrement"
const val CONFIGURE_BUTTON_TAG = "configure"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountingView(
    state: CrowdTallyScreenState.Counting,
    onIncrementIntent: () -> Unit,
    onDecrementIntent: () -> Unit,
    onConfigureIntent: () -> Unit = {},
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.app_name)) },
                actions = {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .clickable { onConfigureIntent() }
                            .testTag(CONFIGURE_BUTTON_TAG)
                    )
                },
            )
        },
        modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp).testTag(COUNTING_VIEW_TAG),
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = state.counter.value.toString(),
                fontSize = 72.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(vertical = 36.dp)
                    .testTag(COUNTER_VIEW_TAG)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                RoundButton(
                    symbol = '-',
                    onClick = onDecrementIntent,
                    enabled = state.counter.isNotAtMinimum(),
                    modifier = Modifier.testTag(DECREMENT_BUTTON_TAG)
                )
                RoundButton(
                    symbol = '+',
                    onClick = onIncrementIntent,
                    enabled = state.counter.isNotAtMaximum(),
                    modifier = Modifier.testTag(INCREMENT_BUTTON_TAG)
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CountingViewPreview() {
    CountingView(
        state = CrowdTallyScreenState.Counting(CrowdCounter(value = 4, capacity = 10)),
        onIncrementIntent = {},
        onDecrementIntent = {},
        onConfigureIntent = {}
    )
}