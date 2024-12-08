package pt.isel.pdm.tictactoe.ui.game

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import pt.isel.pdm.tictactoe.ui.BaseViewModelActivity
import pt.isel.pdm.tictactoe.ui.components.viewModelInit
import pt.isel.pdm.tictactoe.ui.lobby.LobbyScreen
import pt.isel.pdm.tictactoe.ui.remotegame.RemoteGameActivity
import pt.isel.pdm.tictactoe.ui.theme.TicTacToeTheme

class LobbyActivity : BaseViewModelActivity<LobbyViewModel>() {

    override val viewModel: LobbyViewModel by viewModels {
        viewModelInit {
            LobbyViewModel(
                dependencyContainer.matchMakingService,
                dependencyContainer.settingsService
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
                    navigateToRemoteGame = {
                        navigate<RemoteGameActivity>()
                    }
                )
            }
        }
    }
}
