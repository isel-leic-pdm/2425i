package pt.isel.pdm.firebaseexplorer.background

import android.content.Context
import android.os.Debug
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import pt.isel.pdm.firebaseexplorer.FirebaseExplorerApplication
import pt.isel.pdm.firebaseexplorer.model.database.model.SimplePersistentEntity

class DatabaseWorker(
    appContext: Context,
    workerParams: WorkerParameters
) :
    CoroutineWorker(appContext, workerParams) {


    override suspend fun doWork(): Result {

        Log.d("Worker", "Database Worker Running!")
        val app = (applicationContext as FirebaseExplorerApplication)
        Log.d("Test", app.hashCode().toString())


        val obj = SimplePersistentEntity(
            aString = "Hello from workManager",
            aLong = System.currentTimeMillis()
        )
        app.database.simpleModelDao().insert(obj)
        // Sucess
        return Result.success()
        /*
        return Result.retry()
        return Result.failure()

         */
    }
}