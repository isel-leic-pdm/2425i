package pt.isel.pdm.tictactoe.ui.settings

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import pt.isel.pdm.tictactoe.ui.BaseViewModelActivity
import pt.isel.pdm.tictactoe.ui.components.viewModelInit
import pt.isel.pdm.tictactoe.ui.theme.TicTacToeTheme

class SettingsActivity : BaseViewModelActivity<SettingsViewModel>() {

    override val viewModel: SettingsViewModel by viewModels {
        viewModelInit {
            SettingsViewModel(dependencyContainer.settingsService)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TicTacToeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SettingsScreen(viewModel)
                }
            }
        }
    }
}
