package pdm.demos.guessadoodle.preferences

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import pdm.demos.guessadoodle.domain.UserInfo
import pdm.demos.guessadoodle.domain.UserInfoRepository
import pdm.demos.guessadoodle.preferences.views.EditableField

sealed interface PreferencesScreenState {
    data object Initialized : PreferencesScreenState
    data object Loading : PreferencesScreenState
    data class Displaying(val userInfo: UserInfo?) : PreferencesScreenState
    data class Editing(val prevState: Displaying, val selected: EditableField = EditableField.Nick) : PreferencesScreenState
    data class Saving(val userInfo: UserInfo?) : PreferencesScreenState
    data object Exit : PreferencesScreenState
}

/**
 * ViewModel for the Preferences screen. It's the screen state holder and the responsible for the
 * interactions between the screen's views and the remaining of the app.
 */
class PreferencesViewModel(
    private val userInfoRepository: UserInfoRepository,
    initialState: PreferencesScreenState = PreferencesScreenState.Initialized,
    // private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _screenState = MutableStateFlow<PreferencesScreenState>(initialState)
    val screenState: StateFlow<PreferencesScreenState> = _screenState.asStateFlow()

//    private val screenStateKey = "screenState"
//    val screenState: StateFlow<PreferencesScreenState> = savedStateHandle.getStateFlow(screenStateKey, initialState)

    fun loadData(): Job? {

        if (_screenState.value !is PreferencesScreenState.Initialized) {
            return null
        }

        _screenState.value = PreferencesScreenState.Loading

        return viewModelScope.launch {
            val userInfo = userInfoRepository.getUserInfo()
            _screenState.value = PreferencesScreenState.Displaying(userInfo)
        }

//        if (screenState.value !is PreferencesScreenState.Initialized) {
//            return null
//        }
//
//        savedStateHandle[screenStateKey] = PreferencesScreenState.Loading
//
//        return viewModelScope.launch {
//            val userInfo = userInfoRepository.getUserInfo()
//            savedStateHandle[screenStateKey] = PreferencesScreenState.Displaying(userInfo)
//        }
    }

    fun startEditing(selected: EditableField) {
        val currentState = _screenState.value
        if (currentState is PreferencesScreenState.Displaying) {
            _screenState.value = PreferencesScreenState.Editing(currentState, selected)
        }

//        val currentState = screenState.value
//        if (currentState is PreferencesScreenState.Displaying) {
//            savedStateHandle[screenStateKey] = PreferencesScreenState.Editing(currentState, selected)
//        }
    }

    fun cancelEditing() {
        val currentState = _screenState.value
        if (currentState is PreferencesScreenState.Editing) {
            _screenState.value = PreferencesScreenState.Displaying(currentState.prevState.userInfo)
        }

//        val currentState = screenState.value
//        if (currentState is PreferencesScreenState.Editing) {
//            savedStateHandle[screenStateKey] = PreferencesScreenState.Displaying(currentState.prevState.userInfo)
//        }
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

//        if (screenState.value !is PreferencesScreenState.Editing) {
//            return null
//        }
//
//        savedStateHandle[screenStateKey] = PreferencesScreenState.Saving(toSave)
//
//        return viewModelScope.launch {
//            if (toSave != null) userInfoRepository.updateUserInfo(toSave)
//            else userInfoRepository.clearUserInfo()
//            savedStateHandle[screenStateKey] = PreferencesScreenState.Exit
//        }
    }
}

@Suppress("UNCHECKED_CAST")
class PreferencesViewModelFactory(
    private val userInfoRepository: UserInfoRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PreferencesViewModel(userInfoRepository) as T
    }

//    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
//        return PreferencesViewModel(
//            userInfoRepository = userInfoRepository,
//            savedStateHandle = extras.createSavedStateHandle()
//        ) as T
//    }
}
