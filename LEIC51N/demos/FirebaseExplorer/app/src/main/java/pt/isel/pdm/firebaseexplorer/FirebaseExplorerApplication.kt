package pt.isel.pdm.firebaseexplorer

import android.app.Application
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import androidx.work.workDataOf
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import pt.isel.pdm.firebaseexplorer.background.DatabaseWorker
import pt.isel.pdm.firebaseexplorer.background.LogWorker
import pt.isel.pdm.firebaseexplorer.background.ParamsWorker
import pt.isel.pdm.firebaseexplorer.model.database.ApplicationDatabase
import pt.isel.pdm.firebaseexplorer.services.DataStoreSettingsService
import pt.isel.pdm.firebaseexplorer.services.FirestoreTestService
import pt.isel.pdm.firebaseexplorer.services.SettingsService
import pt.isel.pdm.firebaseexplorer.services.TestService
import java.util.concurrent.TimeUnit

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

    override fun onCreate() {
        super.onCreate()

        logWorker()
        //paramWorker()
        //constraintsWorker()
        //periodicWorker()
    }

    private fun logWorker() {

        val workRequest: WorkRequest =
            OneTimeWorkRequestBuilder<LogWorker>()
                .build()

        WorkManager
            .getInstance(this)
            .enqueue(workRequest)
    }

    private fun paramWorker() {

        val workRequest: WorkRequest =
            OneTimeWorkRequestBuilder<ParamsWorker>()
                .setInputData(
                    workDataOf(
                        ParamsWorker.WORKER_PARAM to "COOOL param"
                    )
                )
                .build()

        WorkManager
            .getInstance(this)
            .enqueue(workRequest)
    }

    private fun constraintsWorker() {

        val constraints = Constraints.Builder()
            .setRequiresCharging(false)
            .setRequiredNetworkType(NetworkType.METERED)
            .build()


        val workRequest: WorkRequest =
            OneTimeWorkRequestBuilder<LogWorker>()
                .setConstraints(constraints)
                .build()

        WorkManager
            .getInstance(this)
            .enqueue(workRequest)
    }


    private fun periodicWorker() {

        val uniqueWork = "MyWork"

        val workManager = WorkManager
            .getInstance(this)


        val workRequest =
            PeriodicWorkRequestBuilder<DatabaseWorker>(
                repeatInterval = 15,
                TimeUnit.MINUTES
            )
                .addTag(uniqueWork)
                .build()

        workManager.enqueueUniquePeriodicWork(
            uniqueWork,
            ExistingPeriodicWorkPolicy.CANCEL_AND_REENQUEUE,
            workRequest
        )
    }
}