package pt.isel.pdm.tictactoe

import android.app.Application
import androidx.datastore.preferences.preferencesDataStore
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import pt.isel.pdm.tictactoe.services.AppSettingsService
import pt.isel.pdm.tictactoe.services.DataStoreSettingsService
import pt.isel.pdm.tictactoe.services.firestore.FirestoreMatchmakingService
import pt.isel.pdm.tictactoe.services.MatchmakingService
import pt.isel.pdm.tictactoe.services.RemoteGameService
import pt.isel.pdm.tictactoe.services.firestore.FirestoreRemoteGameService

interface DependencyContainer {
    val settingsService: AppSettingsService
    val matchMakingService: MatchmakingService
    val remoteGameService: RemoteGameService
}

class TicTacToeApplication : Application(), DependencyContainer {


    val firestore: FirebaseFirestore by lazy { Firebase.firestore }

    private val dataStore by preferencesDataStore(
        name = "SettingsDataStore"
    )
    override val settingsService: AppSettingsService by lazy {
        DataStoreSettingsService(dataStore)
    }

    override val matchMakingService: MatchmakingService by lazy {
        FirestoreMatchmakingService(firestore)
    }
    override val remoteGameService: RemoteGameService by lazy {
        FirestoreRemoteGameService(firestore)
    }

    /*
     val database: ApplicationDatabase by lazy {
        Room.databaseBuilder(
            applicationContext,
            ApplicationDatabase::class.java, "MyAwesomeDb"
        )
            .fallbackToDestructiveMigration()
            .build()
    }*/

}