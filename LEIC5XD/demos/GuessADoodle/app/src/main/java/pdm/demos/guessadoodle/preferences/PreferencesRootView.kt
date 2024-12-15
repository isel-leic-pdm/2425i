package pdm.demos.guessadoodle.preferences

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import pdm.demos.guessadoodle.preferences.views.EditableField
import pdm.demos.guessadoodle.preferences.views.PreferencesDisplayView
import pdm.demos.guessadoodle.preferences.views.PreferencesEditView
import pdm.demos.guessadoodle.preferences.views.PreferencesLoadingView
import pdm.demos.guessadoodle.preferences.views.PreferencesSavingView


/**
 * Root view for the Preferences screen. This component is responsible for the interactions between
 * the screen's views and the screen's state, hosted in its view model.
 * @param viewModel the view model for the Preferences screen.
 * @param onBackIntent the callback to be called when the user wants to go back to the previous screen,
 * either because they cancelled the editing or because they saved the changes.
 */
@Composable
fun PreferencesRootView(viewModel: PreferencesViewModel, onBackIntent: () -> Unit) {
    val screenState = viewModel.screenState.collectAsState().value
    when (screenState) {
        is PreferencesScreenState.Initialized,
        is PreferencesScreenState.Loading,
        is PreferencesScreenState.Exit
            -> PreferencesLoadingView()

        is PreferencesScreenState.Displaying -> PreferencesDisplayView(
            state = screenState,
            onEditIntent = { field -> viewModel.startEditing(selected = field) },
            onCancelIntent = onBackIntent
        )

        is PreferencesScreenState.Editing -> PreferencesEditView(
            state = screenState,
            onSaveIntent = { viewModel.saveData(it)?.invokeOnCompletion { onBackIntent() } },
            onCancelIntent = { viewModel.cancelEditing() }
        )

        is PreferencesScreenState.Saving -> PreferencesSavingView(
            state = screenState
        )
    }
}
