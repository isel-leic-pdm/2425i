package pt.isel.pdm.firebaseexplorer

import android.app.Application
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import pt.isel.pdm.firebaseexplorer.model.database.ApplicationDatabase
import pt.isel.pdm.firebaseexplorer.services.DataStoreSettingsService
import pt.isel.pdm.firebaseexplorer.services.FirestoreTestService
import pt.isel.pdm.firebaseexplorer.services.SettingsService
import pt.isel.pdm.firebaseexplorer.services.TestService

// in order for this to work be sure to add your google-services.json file to the project
interface DependencyContainer {
    val firestore: FirebaseFirestore
    val repository: TestService
    val settings: SettingsService
    val database: ApplicationDatabase
}

class FirebaseExplorerApplication : Application(), DependencyContainer {
    private val dataStore by preferencesDataStore(
        name = "SettingsDataStore"
    )
    override val firestore: FirebaseFirestore by lazy { Firebase.firestore }
    override val repository: TestService by lazy { FirestoreTestService(firestore) }
    override val settings: SettingsService by lazy { DataStoreSettingsService(dataStore) }
    override val database: ApplicationDatabase by lazy {
        Room.databaseBuilder(
            applicationContext,
            ApplicationDatabase::class.java, "MyAwesomeDb"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}