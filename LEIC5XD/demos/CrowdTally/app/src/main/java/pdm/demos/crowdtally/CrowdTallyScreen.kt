package pdm.demos.crowdtally

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pdm.demos.crowdtally.ui.theme.CrowdTallyTheme

const val COUNTER_VIEW_TAG = "counter"
const val INCREMENT_BUTTON_TAG = "increment"
const val DECREMENT_BUTTON_TAG = "decrement"

@Composable
fun CrowdTallyScreen(initialCounter: CrowdCounter = CrowdCounter(capacity = 10)) {
    CrowdTallyTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            var counter by remember { mutableStateOf(initialCounter) }
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = counter.value.toString(),
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
                        onClick = { --counter },
                        enabled = counter.isNotAtMinimum(),
                        modifier = Modifier.testTag(DECREMENT_BUTTON_TAG)
                    )
                    RoundButton(
                        symbol = '+',
                        onClick = { ++counter },
                        enabled = counter.isNotAtMaximum(),
                        modifier = Modifier.testTag(INCREMENT_BUTTON_TAG)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CrowdTallyScreenPreview() {
    CrowdTallyScreen()
}