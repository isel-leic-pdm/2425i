package pt.isel.pdm.tictactoe.ui

import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import pt.isel.pdm.tictactoe.ui.components.ErrorMessageComponent
import pt.isel.pdm.tictactoe.ui.components.LoadingAndErrorComponent
import pt.isel.pdm.tictactoe.ui.components.LoadingMessageComponent

abstract class BaseViewModelActivity<T> : BaseActivity() where T : BaseViewModel {
    protected abstract val viewModel: T
    protected fun extendedSetContent(content: @Composable () -> Unit) {
        setContent {
            content()
            LoadingAndErrorComponent(viewModel)

        }
    }


}


