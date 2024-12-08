package pdm.demos.guessadoodle.preferences

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import pdm.demos.guessadoodle.domain.UserInfo
import pdm.demos.guessadoodle.domain.UserInfoRepository

sealed interface PreferencesScreenState {
    data object Initialized : PreferencesScreenState
    data object Loading : PreferencesScreenState
    data class Displaying(val userInfo: UserInfo?) : PreferencesScreenState
    data class Editing(val prevState: Displaying, val nickText: String, val taglineText: String) : PreferencesScreenState
    data class Saving(val userInfo: UserInfo?) : PreferencesScreenState
    data object Exit : PreferencesScreenState
}

/**
 * ViewModel for the Preferences screen. It's the screen state holder and the responsible for the
 * interactions between the screen's views and the remaining of the app.
 */
class PreferencesViewModel(
    private val userInfoRepository: UserInfoRepository,
    initialState: PreferencesScreenState = PreferencesScreenState.Initialized
) : ViewModel() {

    private val _screenState = MutableStateFlow<PreferencesScreenState>(initialState)
    val screenState: StateFlow<PreferencesScreenState> = _screenState.asStateFlow()

    fun loadData(): Job? {
        if (_screenState.value !is PreferencesScreenState.Initialized) {
            return null
        }

        _screenState.value = PreferencesScreenState.Loading

        return viewModelScope.launch {
            val userInfo = userInfoRepository.getUserInfo()
            _screenState.value = PreferencesScreenState.Displaying(userInfo)
        }
    }

    fun startEditing(nickText: String, taglineText: String) {
        val currentState = _screenState.value
        if (currentState is PreferencesScreenState.Displaying) {
            _screenState.value = PreferencesScreenState.Editing(currentState, nickText, taglineText)
        }
    }

    fun cancelEditing() {
        val currentState = _screenState.value
        if (currentState is PreferencesScreenState.Editing) {
            _screenState.value = PreferencesScreenState.Displaying(currentState.prevState.userInfo)
        }
    }

    fun saveData(toSave: UserInfo?): Job? {
        if (_screenState.value !is PreferencesScreenState.Editing) {
            return null
        }

        _screenState.value = PreferencesScreenState.Saving(toSave)

        return viewModelScope.launch {
            if (toSave != null) userInfoRepository.updateUserInfo(toSave)
            else userInfoRepository.clearUserInfo()
            _screenState.value = PreferencesScreenState.Exit
        }
    }
}

@Suppress("UNCHECKED_CAST")
class PreferencesViewModelFactory(private val userInfoRepository: UserInfoRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PreferencesViewModel(userInfoRepository) as T
    }
}
