package pt.isel.pdm.tictactoe.services

import kotlinx.coroutines.flow.Flow

interface AppSettingsService {
    val userName: Flow<String>

    suspend fun setUserName(user: String)
    suspend fun getUserName() : String
}