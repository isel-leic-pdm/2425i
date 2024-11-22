package pdm.demos.guessadoodle.preferences

import androidx.compose.runtime.Composable
import pdm.demos.guessadoodle.preferences.views.PreferencesDisplayView
import pdm.demos.guessadoodle.preferences.views.PreferencesEditView
import pdm.demos.guessadoodle.preferences.views.PreferencesLoadingView
import pdm.demos.guessadoodle.preferences.views.PreferencesSavingView


/**
 * Root view for the Preferences screen. This component is responsible for the interactions between
 * the screen's views and the screen's state, hosted in its view model.
 */
@Composable
fun PreferencesRootView(screenState: PreferencesScreenState) {
    when (screenState) {
        is PreferencesScreenState.Loading,
        is PreferencesScreenState.Initialized -> PreferencesLoadingView()

        is PreferencesScreenState.Displaying -> PreferencesDisplayView(
            state = screenState,
            onEditIntent = { _, _ -> },
            onCancelIntent = { }
        )

        is PreferencesScreenState.Editing -> PreferencesEditView(
            state = screenState,
            onSaveIntent = { },
            onCancelIntent = { }
        )

        is PreferencesScreenState.Saving -> PreferencesSavingView(
            state = screenState
        )
    }
}
