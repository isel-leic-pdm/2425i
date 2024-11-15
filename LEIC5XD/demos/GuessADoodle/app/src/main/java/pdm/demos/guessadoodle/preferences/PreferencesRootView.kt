package pdm.demos.guessadoodle.preferences

import androidx.compose.runtime.Composable
import pdm.demos.guessadoodle.domain.Nick
import pdm.demos.guessadoodle.preferences.views.PreferencesDisplayView
import pdm.demos.guessadoodle.preferences.views.PreferencesEditView
import pdm.demos.guessadoodle.preferences.views.PreferencesLoadingView
import pdm.demos.guessadoodle.preferences.views.PreferencesSavingView

sealed interface PreferencesScreenState {
    data object Loading : PreferencesScreenState
    data class Displaying(val nick: Nick) : PreferencesScreenState
    data class Editing(val nick: Nick) : PreferencesScreenState
    data object Saving : PreferencesScreenState
}

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
