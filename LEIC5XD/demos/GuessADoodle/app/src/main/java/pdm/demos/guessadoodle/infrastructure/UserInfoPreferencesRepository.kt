package pdm.demos.guessadoodle.infrastructure

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import pdm.demos.guessadoodle.domain.Nick
import pdm.demos.guessadoodle.domain.UserInfo
import pdm.demos.guessadoodle.domain.UserInfoRepository

/**
 * A [UserInfoRepository] implementation that stores user info in a [DataStore].
 */
class UserInfoPreferencesRepository(private val store: DataStore<Preferences>) : UserInfoRepository {

    override val userInfo: Flow<UserInfo?> =
        store.data.map { preferences -> preferences.toUserInfo() }

    override suspend fun getUserInfo(): UserInfo? {
        val preferences: Preferences = store.data.first()
        return preferences.toUserInfo()
    }

    override suspend fun updateUserInfo(userInfo: UserInfo) {
        store.edit(transform = userInfo::writeToPreferences)
    }

    override suspend fun clearUserInfo() {
        store.edit { it.clear() }
    }
}

// Keys for the user info data.

private val nickKey = stringPreferencesKey("nick")
private val taglineKey = stringPreferencesKey("tagline")

// Utility functions for converting between UserInfo and Preferences.

private fun Preferences.toUserInfo(): UserInfo? =
    this[nickKey]?.let {
        UserInfo(Nick(it), this[taglineKey])
    }

private fun UserInfo.writeToPreferences(preferences: MutablePreferences) {
    preferences[nickKey] = nick.value
    if (tagline != null) preferences[taglineKey] = tagline
}

