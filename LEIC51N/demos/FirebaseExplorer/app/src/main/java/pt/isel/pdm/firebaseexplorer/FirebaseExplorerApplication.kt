package pt.isel.pdm.firebaseexplorer

import android.app.Application
import com.google.firebase.Firebase
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import pt.isel.pdm.firebaseexplorer.data.FirestoreRepository
import pt.isel.pdm.firebaseexplorer.data.TestRepository

// in order for this to work be sure to add your google-services.json file to the project
interface DependencyContainer{
    val firestore : FirebaseFirestore
    val repository : TestRepository
}

class FirebaseExplorerApplication : Application(), DependencyContainer {
    override val firestore: FirebaseFirestore by lazy { Firebase.firestore }
    override val repository: TestRepository by lazy { FirestoreRepository(firestore) }
}