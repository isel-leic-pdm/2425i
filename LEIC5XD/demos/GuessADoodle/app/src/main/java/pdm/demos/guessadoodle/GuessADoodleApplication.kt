package pdm.demos.guessadoodle

import android.app.Application
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.MemoryCacheSettings
import com.google.firebase.firestore.firestore

const val TAG = "Guess-A-Doodle"

class GuessADoodleApplication : Application() {

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
}
