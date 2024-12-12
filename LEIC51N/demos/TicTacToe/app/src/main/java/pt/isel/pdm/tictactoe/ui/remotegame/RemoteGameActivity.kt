package pt.isel.pdm.tictactoe.ui.remotegame

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import pt.isel.pdm.tictactoe.domain.GameInfo
import pt.isel.pdm.tictactoe.ui.BaseActivity
import pt.isel.pdm.tictactoe.ui.BaseViewModelActivity
import pt.isel.pdm.tictactoe.ui.components.viewModelInit
import pt.isel.pdm.tictactoe.ui.theme.TicTacToeTheme


class RemoteGameActivity : BaseViewModelActivity<RemoteGameViewModel>() {

    companion object {
        const val GAME_INFO_EXTRA_NAME = "___gameidextra"

        fun navigate(ctx: BaseActivity, info: GameInfo) {
            ctx.navigate<RemoteGameActivity> {
                it.putExtra(GAME_INFO_EXTRA_NAME, info)
            }
        }
    }

    override val viewModel: RemoteGameViewModel by viewModels {
        viewModelInit {
            RemoteGameViewModel(
                dependencyContainer.remoteGameService
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val obj = intent.getParcelableExtra(GAME_INFO_EXTRA_NAME, GameInfo::class.java)
        if (obj == null) {
            finish()
            return
        }
        viewModel.start(obj)

        setContent {
            TicTacToeTheme {
                RemoteGameScreen(
                    viewModel = viewModel,
                    goBackAction = { finish() }
                )
            }
        }
    }
}
