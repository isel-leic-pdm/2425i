package pdm.demos.guessadoodle.preferences

import androidx.compose.runtime.Composable
import pdm.demos.guessadoodle.preferences.views.PreferencesDisplayView
import pdm.demos.guessadoodle.preferences.views.PreferencesEditView
import pdm.demos.guessadoodle.preferences.views.PreferencesLoadingView
import pdm.demos.guessadoodle.preferences.views.PreferencesSavingView

@Composable
fun PreferencesRootView(screenState: PreferencesScreenState) {
    when (screenState) {
        is PreferencesScreenState.Loading -> PreferencesLoadingView()
        is PreferencesScreenState.Displaying -> PreferencesDisplayView(
            state = screenState,
            onEditIntent = {},
            onCancelIntent = {}
        )
        is PreferencesScreenState.Editing -> PreferencesEditView(
            state = screenState,
            onSaveIntent = {},
            onCancelIntent = {}
        )
        is PreferencesScreenState.Saving -> PreferencesSavingView()
    }
}
