package pt.isel.pdm.firebaseexplorer.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class DataStoreSettingsService(
    private val dataStore: DataStore<Preferences>
) : SettingsService {

    val INT_PROP = intPreferencesKey("int_prop")
    val STR_PROP = stringPreferencesKey("str_prop")

    override val stringPreference: Flow<String> =
        dataStore.data.map { prefs ->
            prefs[STR_PROP] ?: ""
        }

    override val intPreference: Flow<Int> = dataStore.data.map { prefs ->
        prefs[INT_PROP] ?: -1

    }

    override suspend fun updateStringPreference(str: String) {

        dataStore.edit { prefs ->
            prefs[STR_PROP] = str
        }
    }

    override suspend fun updateIntPreference(number: Int) {
        dataStore.edit { prefs ->
            prefs[INT_PROP] = number
        }
    }

    override suspend fun clearPreferences() {
        dataStore.edit {
            it.remove(INT_PROP)
            it.remove(STR_PROP)
        }
    }
}