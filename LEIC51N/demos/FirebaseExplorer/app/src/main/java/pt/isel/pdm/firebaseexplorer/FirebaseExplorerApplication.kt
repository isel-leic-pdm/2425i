package pt.isel.pdm.firebaseexplorer

import android.app.Application
import androidx.datastore.preferences.preferencesDataStore
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import pt.isel.pdm.firebaseexplorer.data.DataStoreSettingsService
import pt.isel.pdm.firebaseexplorer.data.FirestoreTestService
import pt.isel.pdm.firebaseexplorer.data.SettingsService
import pt.isel.pdm.firebaseexplorer.data.TestService

// in order for this to work be sure to add your google-services.json file to the project
interface DependencyContainer{
    val firestore : FirebaseFirestore
    val repository : TestService
    val settings: SettingsService
}

class FirebaseExplorerApplication : Application(), DependencyContainer {
    private val dataStore by preferencesDataStore(
        name = "SettingsDataStore"
    )
    override val firestore: FirebaseFirestore by lazy { Firebase.firestore }
    override val repository: TestService by lazy { FirestoreTestService(firestore) }
    override val settings: SettingsService by lazy { DataStoreSettingsService(dataStore) }
}