package pt.isel.pdm.firebaseexplorer

import android.app.Application
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import pt.isel.pdm.firebaseexplorer.data.FirestoreTestService
import pt.isel.pdm.firebaseexplorer.data.TestService

// in order for this to work be sure to add your google-services.json file to the project
interface DependencyContainer{
    val firestore : FirebaseFirestore
    val repository : TestService
}

class FirebaseExplorerApplication : Application(), DependencyContainer {
    override val firestore: FirebaseFirestore by lazy { Firebase.firestore }
    override val repository: TestService by lazy { FirestoreTestService(firestore) }
}