package pt.isel.pdm.crowdtally.Main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pt.isel.pdm.crowdtally.R
import pt.isel.pdm.crowdtally.TestTags
import pt.isel.pdm.crowdtally.TestTags.CrowdTallyViewIncButton


@Composable
fun CrowdTallyView(
    crowd: Int,
    onIncrementAvailable: Boolean,
    onDecrementAvailable: Boolean,
    onIncrementCurrent: () -> Unit,
    onDecrementCurrent: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ArrowButton(
            click = onIncrementCurrent,
            modifier = Modifier.rotate(-90f).testTag(TestTags.CrowdTallyViewIncButton),
            isButtonEnable = onIncrementAvailable
        )

        Text(
            text = crowd.toString(),
            fontSize = 80.sp,
            modifier = Modifier.padding(24.dp)
        )

        ArrowButton(
            click = onDecrementCurrent,
            isButtonEnable = onDecrementAvailable,
            modifier = Modifier.rotate(90f).testTag(TestTags.CrowdTallyViewDecButton)
        )

    }
}

@Composable
private fun ArrowButton(
    click: () -> Unit,
    isButtonEnable: Boolean = true,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = click,
        modifier = modifier.size(100.dp),
        enabled = isButtonEnable
    ) {
        Icon(
            Icons.Default.PlayArrow,
            contentDescription = null,
        )
    }
}

@Composable
fun CrowdTallyConfigurator(
    currentMax: Int,
    onConfigurationChanged: (Int) -> Unit
) {
    var maxStateString by rememberSaveable() { mutableStateOf(currentMax.toString()) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TextField(
            value = maxStateString,
            label = { Text(stringResource(R.string.new_max_crowd)) },
            onValueChange = { newValue ->
                maxStateString = newValue
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )

        Button(
            {
                onConfigurationChanged(maxStateString.toInt())
            },
            enabled = maxStateString.length > 0 && maxStateString.toIntOrNull() != null
        ) {
            Text(stringResource(R.string.commit))
        }

    }
}