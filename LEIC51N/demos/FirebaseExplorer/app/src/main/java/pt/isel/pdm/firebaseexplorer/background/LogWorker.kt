package pt.isel.pdm.firebaseexplorer.background

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class LogWorker(appContext: Context, workerParams: WorkerParameters):
    Worker(appContext, workerParams) {
    override fun doWork(): Result {

        Log.d("Worker", "I'm doing some work nice isn't it?")

        // Sucess
        return Result.success()
        /*
        return Result.retry()
        return Result.failure()

         */
    }
}