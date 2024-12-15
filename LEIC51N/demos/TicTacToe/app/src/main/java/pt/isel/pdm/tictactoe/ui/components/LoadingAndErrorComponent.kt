package pt.isel.pdm.tictactoe.ui.components

import androidx.compose.runtime.Composable
import pt.isel.pdm.tictactoe.ui.BaseViewModel
import pt.isel.pdm.tictactoe.ui.ViewModelOperationState

@Composable
 fun LoadingAndErrorComponent(viewModel: BaseViewModel) {

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