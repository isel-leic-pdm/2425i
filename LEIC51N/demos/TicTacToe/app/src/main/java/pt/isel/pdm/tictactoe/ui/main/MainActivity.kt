package pt.isel.pdm.tictactoe.ui.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import pt.isel.pdm.tictactoe.ui.BaseViewModelActivity
import pt.isel.pdm.tictactoe.ui.components.viewModelInit
import pt.isel.pdm.tictactoe.ui.game.GameActivity
import pt.isel.pdm.tictactoe.ui.game.LobbyActivity
import pt.isel.pdm.tictactoe.ui.settings.SettingsActivity
import pt.isel.pdm.tictactoe.ui.theme.TicTacToeTheme

class MainActivity : BaseViewModelActivity<MainViewModel>() {

    override val viewModel: MainViewModel by viewModels {
        viewModelInit {
            MainViewModel(dependencyContainer.settingsService)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TicTacToeTheme {
                MainScreen(
                    viewModel = viewModel,
                    navigateToOfflineGame = { navigate<GameActivity>() },
                    navigateToSettings = { navigate<SettingsActivity>() },
                    navigateToOnLineGame = { navigate<LobbyActivity>() }
                )
            }
        }
    }
}
