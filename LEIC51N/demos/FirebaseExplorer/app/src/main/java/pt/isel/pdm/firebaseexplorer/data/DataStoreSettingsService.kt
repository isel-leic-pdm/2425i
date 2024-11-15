package pt.isel.pdm.firebaseexplorer.data

import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import pt.isel.pdm.firebaseexplorer.model.SimpleModel

class DataStoreSettingsService(
    private val dataStore: DataStore<Preferences>,
) : SettingsService {

    private val INT_PROP = intPreferencesKey("int_prop")
    private val STR_PROP = stringPreferencesKey("str_prop")

    private val SM_INT_PROP = intPreferencesKey("int_prop")
    private val SM_STR_PROP = stringPreferencesKey("str_prop")



    override val stringPreference: Flow<String> =
        dataStore.data
            .catch { err ->
                if (err is IOException)
                    emit(emptyPreferences())
                else
                    throw err
            }
            .map { prefs ->
                prefs[STR_PROP] ?: ""
            }

    override val intPreference: Flow<Int> = dataStore.data
        .catch { err ->
            if (err is IOException)
                emit(emptyPreferences())
            else
                throw err
        }
        .map { prefs ->
            prefs[INT_PROP] ?: -1

        }
    override val simpleModelPreference: Flow<SimpleModel> = dataStore.data
        .catch { err ->
            if (err is IOException)
                emit(emptyPreferences())
            else
                throw err
        }.map {
            SimpleModel(
                it[SM_STR_PROP] ?: "",
                it[SM_INT_PROP] ?: -1,
                emptyList()
            )

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

    override suspend fun updateSimpleModel(model: SimpleModel) {
        dataStore.edit {
            it[SM_INT_PROP] = model.number
            it[SM_STR_PROP] = model.id
        }
    }

    override suspend fun getIntPreference(): Int {
        return dataStore.data.first().get(INT_PROP) ?: -1
    }

    override suspend fun getStringPreference(): String {
        return dataStore.data.first().get(STR_PROP) ?: ""
    }

    override suspend fun clearPreferences() {
        dataStore.edit {
            it.remove(INT_PROP)
            it.remove(STR_PROP)
        }
    }
}