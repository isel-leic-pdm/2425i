package pdm.demos.guessadoodle.preferences

import androidx.compose.runtime.Composable
import pdm.demos.guessadoodle.domain.Nick

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
        is PreferencesScreenState.Editing -> PreferencesEditView(screenState)
        is PreferencesScreenState.Saving -> PreferencesSavingView()
    }
}
