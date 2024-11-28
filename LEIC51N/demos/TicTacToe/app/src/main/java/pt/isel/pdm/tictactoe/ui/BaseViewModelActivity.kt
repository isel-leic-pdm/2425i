package pt.isel.pdm.tictactoe.ui

import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import pt.isel.pdm.tictactoe.ui.components.ErrorMessageComponent
import pt.isel.pdm.tictactoe.ui.components.LoadingMessageComponent

abstract class BaseViewModelActivity<T> : BaseActivity() where T : BaseViewModel {
    protected abstract val viewModel: T
    protected fun extendedSetContent(content: @Composable () -> Unit) {
        setContent {
            content()
            LoadingAndError(viewModel)
        }
    }

    @Composable
    private fun LoadingAndError(viewModel: T) {

        when (val state = viewModel.viewOperationState) {
            is ViewModelOperationState.Idle -> {}
            is ViewModelOperationState.Loading -> LoadingMessageComponent()
            is ViewModelOperationState.Error -> {
                ErrorMessageComponent(
                    error = state.exception,
                    onDismiss = viewModel::errorDismiss,
                    onRetry = if (state.canRetry) viewModel::errorRetry else null
                )

            }

        }


    }
}


