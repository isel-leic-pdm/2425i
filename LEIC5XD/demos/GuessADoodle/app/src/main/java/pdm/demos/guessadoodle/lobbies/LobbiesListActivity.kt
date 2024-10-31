package pdm.demos.guessadoodle.lobbies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import pdm.demos.guessadoodle.GuessADoodleApplication
import pdm.demos.guessadoodle.ui.theme.GuessADoodleTheme

class LobbiesListActivity : ComponentActivity() {

    private val db by lazy {
        (application as GuessADoodleApplication).emulatedDb
    }

    private val viewModel by viewModels<LobbiesListViewModel>(
        factoryProducer = {
            LobbiesListViewModelFactory(db)
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GuessADoodleTheme {
                LobbiesListRootView(viewModel)
            }
        }
    }
}
