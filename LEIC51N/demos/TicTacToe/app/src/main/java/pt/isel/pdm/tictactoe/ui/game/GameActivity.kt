package pt.isel.pdm.tictactoe.ui.game

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import pt.isel.pdm.tictactoe.ui.BaseViewModelActivity
import pt.isel.pdm.tictactoe.ui.components.viewModelInit

class GameActivity : BaseViewModelActivity<GameViewModel>() {

    override val viewModel: GameViewModel by viewModels {
        viewModelInit {
            GameViewModel()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        extendedSetContent {
            GameScreen(viewModel)

        }
    }
}
