package pt.isel.pdm.tictactoe.ui.game

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import pt.isel.pdm.tictactoe.domain.GameLobby
import pt.isel.pdm.tictactoe.ui.BaseViewModelActivity
import pt.isel.pdm.tictactoe.ui.components.initWithSavedState
import pt.isel.pdm.tictactoe.ui.components.viewModelInit
import pt.isel.pdm.tictactoe.ui.lobby.LobbyScreen
import pt.isel.pdm.tictactoe.ui.lobby.LobbyViewModel
import pt.isel.pdm.tictactoe.ui.remotegame.RemoteGameActivity
import pt.isel.pdm.tictactoe.ui.theme.TicTacToeTheme

class LobbyActivity : BaseViewModelActivity<LobbyViewModel>() {

    override val viewModel: LobbyViewModel by viewModels {
        initWithSavedState { savedState ->
            LobbyViewModel(
                dependencyContainer.matchMakingService,
                dependencyContainer.settingsService,
                savedState
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            TicTacToeTheme {
                LobbyScreen(
                    viewModel = viewModel,
                    navigateBack = { finish() },
                    navigateToRemoteGame = { game ->
                        RemoteGameActivity.navigate(this, game)
                    }
                )
            }
        }
    }
}
