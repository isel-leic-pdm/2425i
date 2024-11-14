package pt.isel.pdm.firebaseexplorer.data

import kotlinx.coroutines.flow.Flow

interface SettingsService {
    val stringPreference : Flow<String>
    val intPreference : Flow<Int>

    suspend fun updateStringPreference(str: String)
    suspend fun updateIntPreference(number: Int)

    suspend fun clearPreferences()
}