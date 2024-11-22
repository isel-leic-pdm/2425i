package pdm.demos.guessadoodle

import android.app.Application
import androidx.datastore.preferences.preferencesDataStore
import pdm.demos.guessadoodle.domain.UserInfoRepository

class GuessADoodleTestApplication : Application(), DependenciesContainer {

    override val preferencesDataStore by preferencesDataStore("test_prefs")

    override val userInfoRepository: UserInfoRepository
        get() = TODO("Not yet implemented")
}
