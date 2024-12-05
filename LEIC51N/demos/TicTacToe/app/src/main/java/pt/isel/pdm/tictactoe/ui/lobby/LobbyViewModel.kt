package pt.isel.pdm.tictactoe.ui.game

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import pt.isel.pdm.tictactoe.domain.GameLobby
import pt.isel.pdm.tictactoe.domain.GameSession
import pt.isel.pdm.tictactoe.services.AppSettingsService
import pt.isel.pdm.tictactoe.services.MatchmakingService
import pt.isel.pdm.tictactoe.ui.BaseViewModel

class LobbyViewModel(
    private val matchmakingService: MatchmakingService,
    private val settingsService: AppSettingsService
) : BaseViewModel() {

    val lobbies = matchmakingService.activeLobbies()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            emptyList()
        )


    val gameSessionFlow
        get() = gameSessionMutableFlow


    private val gameSessionMutableFlow = MutableStateFlow<GameSession?>(null)
    private var lobbyWaitJob: Job? = null


    fun joinLobby(lobby: GameLobby) {
        lobbyWaitJob = viewModelAction {
            val session = matchmakingService.joinLobby(settingsService.getUserName(), lobby)
            gameSessionMutableFlow.emit(session)
        }
    }

    fun createAndWaitInNewLobby() {
        lobbyWaitJob = viewModelActionWithRetry {
            val session =
                matchmakingService.createLobbyAndWaitForPlayer(settingsService.getUserName())
            gameSessionMutableFlow.emit(session)
        }
    }

    fun cancelJoinLobby() {
        lobbyWaitJob?.cancel()
    }
}