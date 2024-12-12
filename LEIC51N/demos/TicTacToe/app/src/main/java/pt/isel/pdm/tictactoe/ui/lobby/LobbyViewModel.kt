package pt.isel.pdm.tictactoe.ui.lobby

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.saveable
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import pt.isel.pdm.tictactoe.domain.GameInfo
import pt.isel.pdm.tictactoe.domain.GameLobby
import pt.isel.pdm.tictactoe.domain.GameSession
import pt.isel.pdm.tictactoe.services.AppSettingsService
import pt.isel.pdm.tictactoe.services.MatchmakingService
import pt.isel.pdm.tictactoe.ui.BaseViewModel
import pt.isel.pdm.tictactoe.ui.ViewModelOperationState
import kotlin.coroutines.cancellation.CancellationException

class LobbyViewModel(
    private val matchmakingService: MatchmakingService,
    private val settingsService: AppSettingsService,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    val SAVABLE_LOBBY_ID = "SAVABLE_LOBBY_ID"
    val lobbies = matchmakingService.activeLobbies()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            emptyList()
        )


    val gameSessionFlow
        get() = gameSessionMutableFlow


    private val gameSessionMutableFlow = MutableStateFlow<GameInfo?>(null)


    init {
        val lobbyId = savedStateHandle.get<String>(SAVABLE_LOBBY_ID)
        if (lobbyId != null)
            joinLobby(GameLobby("", lobbyId))
    }

    fun joinLobby(lobby: GameLobby) {
        viewModelAction {

            try {
                savedStateHandle[SAVABLE_LOBBY_ID] = lobby.id
                delay(5000)
                val session = matchmakingService.joinLobby(settingsService.getUserName(), lobby)
                gameSessionMutableFlow.emit(session)
            } catch (_: CancellationException) {

            }
        }
    }

    fun createAndWaitInNewLobby() {
        viewModelActionWithRetry {
            try {
                val session =
                    matchmakingService.createLobbyAndWaitForPlayer(settingsService.getUserName())
                gameSessionMutableFlow.emit(session)
            } catch (_: CancellationException) {

            }
        }
    }

    fun cancelJoinLobby() {
        val state = viewOperationState
        if (state !is ViewModelOperationState.Loading)
            return

        state.job.cancel()
    }
}