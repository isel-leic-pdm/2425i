package pt.isel.pdm.tictactoe.ui.settings

import android.util.Log
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import pt.isel.pdm.tictactoe.services.AppSettingsService
import pt.isel.pdm.tictactoe.ui.BaseViewModel

class SettingsViewModel(
    private val settingsService: AppSettingsService
) : BaseViewModel() {

    val userNameFlow = settingsService.userName
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            ""
        )

    fun changeUserName(userName: String) = viewModelActionWithRetry {
        settingsService.setUserName(userName)
    }


    /*
    similar to userNameFlow, functionality wise for the view
     init {
        viewModelScope.launch {
            settingsService.userName.collect {
                userNameFlowMutable.emit(it)
            }
        }
    }

    private val userNameFlowMutable = MutableStateFlow<String>("")

    val userNameFlow2: StateFlow<String>
        get() = userNameFlowMutable;



     */
}