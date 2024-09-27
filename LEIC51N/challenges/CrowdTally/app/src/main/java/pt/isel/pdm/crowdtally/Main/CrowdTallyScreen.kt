package pt.isel.pdm.crowdtally.Main

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import pt.isel.pdm.crowdtally.R
import pt.isel.pdm.crowdtally.ui.theme.CrowdTallyTheme

private val stateSaver = Saver<CrowdTally, List<Int>>(
    save = { toSave ->
        listOf(toSave.currentCrowd, toSave.maxCrowd)
    },
    restore = { toRestore ->
        if (toRestore.isEmpty())
            CrowdTally.default
        else
            CrowdTally(toRestore[0], toRestore[1])
    }
)

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable
fun CrowdTallyScreen() {
    var state by rememberSaveable(stateSaver = stateSaver) { mutableStateOf(CrowdTally.default) }
    var isChangingMaxCrowd by rememberSaveable { mutableStateOf(false) }
    CrowdTallyTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = { Text(stringResource(R.string.app_name)) },
                    actions = {
                        if (isChangingMaxCrowd == false)
                            Button(
                                onClick = {
                                    isChangingMaxCrowd = true
                                },
                            ) {
                                Icon(imageVector = Icons.Default.Edit, contentDescription = "")
                            }
                    }
                )
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier.padding(innerPadding)
            ) {

                CrowdTallyView(
                    crowd = state.currentCrowd,
                    onDecrementAvailable = state.isEmpty == false,
                    onIncrementAvailable = state.isFull == false,
                    onIncrementCurrent = {
                        state = state.increment()
                        Log.d("", "increment")

                    },
                    onDecrementCurrent = {
                        state = state.decrement()
                        Log.d("", "decrement")
                    }
                )

                if (isChangingMaxCrowd)
                    CrowdTallyConfigurator(
                        currentMax = state.maxCrowd,
                        onConfigurationChanged = { newMax ->
                            state = state.changeMaxCapacity(newMax)
                            isChangingMaxCrowd = false
                        }

                    )
            }
        }
    }
}

