package pt.isel.pdm.firebaseexplorer.screens.flow

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import pt.isel.pdm.firebaseexplorer.screens.helpers.viewModelInit
import pt.isel.pdm.firebaseexplorer.screens.theme.FirebaseExplorerTheme
import java.util.concurrent.Flow

class FlowPlaygroundActivity : ComponentActivity() {

    val viewModel by viewModels<FlowPlaygroundViewModel> {
        viewModelInit {
            FlowPlaygroundViewModel()
        }
    }

    companion object {
        fun navigate(ctx: Context) {
            val intent = Intent(ctx, FlowPlaygroundActivity::class.java)
            ctx.startActivity(intent)

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FirebaseExplorerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FirebaseExplorerTheme {
        Greeting("Android")
    }
}