package pt.isel.pdm.tictactoe.ui.main

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import pt.isel.pdm.tictactoe.services.AppSettingsService
import pt.isel.pdm.tictactoe.ui.BaseViewModel

class MainViewModel(
    settingsService: AppSettingsService
) : BaseViewModel() {

    val userName = settingsService.userName.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        ""
    )
}