package pt.isel.pdm.nasaimageoftheday.ui.main

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
import pt.isel.pdm.nasaimageoftheday.DependencyContainer
import pt.isel.pdm.nasaimageoftheday.NasaImageOfTheDayApplication
import pt.isel.pdm.nasaimageoftheday.ui.about.AboutActivity
import pt.isel.pdm.nasaimageoftheday.ui.extensions.initWithSavedState
import pt.isel.pdm.nasaimageoftheday.ui.theme.NasaImageOfTheDayTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel> {
        initWithSavedState {
            MainViewModel((application as DependencyContainer).nasaService)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NasaImageOfTheDayTheme {
                MainScreen(viewModel, aboutRequest = {
                    AboutActivity.navigate(this)
                })
            }
        }
    }
}
