package pdm.demos.guessadoodle.infrastructure

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import pdm.demos.guessadoodle.domain.Nick
import pdm.demos.guessadoodle.domain.NickRepository

class NickPreferencesRepository(private val store: DataStore<Preferences>) : NickRepository {

    private val nickKey = stringPreferencesKey("nick")
    override val nick: Flow<Nick?> =
        store.data.map { preferences ->
            preferences[nickKey]?.let {
                Nick(it)
            }
        }

    override suspend fun getNick(): Nick? {
        val preferences: Preferences = store.data.first()
        return preferences[nickKey]?.let {
            Nick(it)
        }
    }

    override suspend fun updateNick(nick: Nick) {
        store.edit { preferences ->
            preferences[nickKey] = nick.value
        }
    }
}