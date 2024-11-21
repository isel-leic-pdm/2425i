package pt.isel.pdm.firebaseexplorer.screens.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.isel.pdm.firebaseexplorer.DependencyContainer
import pt.isel.pdm.firebaseexplorer.FirebaseExplorerApplication
import pt.isel.pdm.firebaseexplorer.model.SimpleModel
import pt.isel.pdm.firebaseexplorer.screens.detail.DetailActivity
import pt.isel.pdm.firebaseexplorer.screens.flow.FlowPlaygroundActivity
import pt.isel.pdm.firebaseexplorer.screens.helpers.viewModelInit
import pt.isel.pdm.firebaseexplorer.screens.room.RoomPlaygroundActivity
import pt.isel.pdm.firebaseexplorer.screens.theme.FirebaseExplorerTheme
import java.util.UUID

class MainActivity : ComponentActivity() {

    val viewModel by viewModels<MainViewModel> {
        viewModelInit {
            MainViewModel((application as DependencyContainer).repository)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FirebaseExplorerTheme {
                MainScreen(
                    viewModel = viewModel,
                    navigateToDetail = { model ->
                        DetailActivity.navigate(this, model)
                    },
                    navigateToFlow = {
                        FlowPlaygroundActivity.navigate(this)
                    },
                    navigateToRoom = {
                        RoomPlaygroundActivity.navigate(this)
                    })
            }
        }
    }


}

