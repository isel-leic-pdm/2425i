package pt.isel.pdm.tictactoe.services

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreSettingsService(
    private val dataStore: DataStore<Preferences>,
) : AppSettingsService {

    private val USERNAME_PROP = stringPreferencesKey("ttt_username")

    override val userName: Flow<String>
        get() = dataStore.data.map { prefs ->
            prefs[USERNAME_PROP]?:""
        }

    override suspend fun setUserName(user: String) {
        dataStore.edit { prefs ->
            prefs[USERNAME_PROP] = user
        }
    }

}