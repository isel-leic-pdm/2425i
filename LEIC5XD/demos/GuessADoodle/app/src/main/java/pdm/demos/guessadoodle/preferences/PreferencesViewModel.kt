package pdm.demos.guessadoodle.preferences

import androidx.lifecycle.ViewModel
import pdm.demos.guessadoodle.domain.Nick
import pdm.demos.guessadoodle.domain.NickRepository

sealed interface PreferencesScreenState {
    data object Initialized : PreferencesScreenState
    data object Loading : PreferencesScreenState
    data class Displaying(val nick: Nick) : PreferencesScreenState
    data class Editing(val nick: Nick) : PreferencesScreenState
    data object Saving : PreferencesScreenState
}

class PreferencesViewModel(
    private val nickRepository: NickRepository,
    initialState: PreferencesScreenState = PreferencesScreenState.Initialized
) : ViewModel() {
}