package pt.isel.pdm.firebaseexplorer.data

import kotlinx.coroutines.flow.Flow
import pt.isel.pdm.firebaseexplorer.model.SimpleModel

interface SettingsService {
    val stringPreference: Flow<String>
    val intPreference: Flow<Int>
    val simpleModelPreference : Flow<SimpleModel>

    suspend fun updateStringPreference(str: String)
    suspend fun updateIntPreference(number: Int)
    suspend fun updateSimpleModel(model : SimpleModel)


    suspend fun getIntPreference(): Int
    suspend fun getStringPreference(): String

    suspend fun clearPreferences()
}