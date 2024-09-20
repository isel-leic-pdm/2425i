package pt.isel.pdm.crowdtally

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pt.isel.pdm.crowdtally.ui.theme.CrowdTallyTheme

class MainActivity : ComponentActivity() {

    val LogTag = "CrowdTally"
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(LogTag, "onCreate")
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CrowdTallyScreen()
        }
    }

    override fun onPause() {
        Log.d(LogTag, "onPause")
        super.onPause()
    }

    override fun onDestroy() {
        Log.d(LogTag, "onDestroy")
        super.onDestroy()
    }


    override fun onStop() {
        Log.d(LogTag, "onStop")
        super.onStop()
    }

    override fun onStart() {
        Log.d(LogTag, "onStart")
        super.onStart()
    }

}


@Preview(showSystemUi = true)
@Composable
private fun CrowdTallyScreen() {
    var crowd by remember { mutableIntStateOf(0) }
    CrowdTallyTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                ArrowButton(
                    click = { crowd++ },
                    modifier = Modifier.rotate(-90f)
                )

                Text(
                    text = crowd.toString(),
                    fontSize = 80.sp,
                    modifier = Modifier.padding(24.dp)
                )

                ArrowButton(
                    click = { crowd-- },
                    isButtonEnable = crowd > 0,
                    modifier = Modifier.rotate(90f)
                )

            }
        }
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
