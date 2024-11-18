package pdm.demos.guessadoodle

import android.app.Application
import androidx.datastore.preferences.preferencesDataStore

class GuessADoodleTestApplication : Application(), DependenciesContainer {

    override val preferencesDataStore by preferencesDataStore("test_prefs")
}
