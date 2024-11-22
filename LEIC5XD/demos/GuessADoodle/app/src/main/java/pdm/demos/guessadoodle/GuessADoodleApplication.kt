package pdm.demos.guessadoodle

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.MemoryCacheSettings
import com.google.firebase.firestore.firestore
import pdm.demos.guessadoodle.domain.UserInfoRepository
import pdm.demos.guessadoodle.infrastructure.UserInfoPreferencesRepository

const val TAG = "Guess-A-Doodle"

/**
 * The application's dependencies container, that is globally accessible objects can be retrieved
 * from here.
 */
interface DependenciesContainer {
    val preferencesDataStore: DataStore<Preferences>
    val userInfoRepository: UserInfoRepository
}

class GuessADoodleApplication : Application(), DependenciesContainer {

    val emulatedDb: FirebaseFirestore by lazy {
        Firebase.firestore.also {
            it.useEmulator("10.0.2.2", 8080)
            it.firestoreSettings = FirebaseFirestoreSettings.Builder()
                .setLocalCacheSettings(MemoryCacheSettings.newBuilder().build())
                .build()
        }
    }

    val realDb: FirebaseFirestore by lazy {
        Firebase.firestore
    }

    override val preferencesDataStore: DataStore<Preferences> by preferencesDataStore(name = "preferences")

    override val userInfoRepository: UserInfoRepository by lazy {
        UserInfoPreferencesRepository(preferencesDataStore)
    }
}
