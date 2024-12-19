package pt.isel.pdm.firebaseexplorer.background

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class ParamsWorker(
    appContext: Context,
    private val workerParams: WorkerParameters
) :
    Worker(appContext, workerParams) {

companion object
{
    const val WORKER_PARAM = "WORKER_PARAM"
}
    override fun doWork(): Result {

        workerParams.inputData
        val param = workerParams.inputData.getString(WORKER_PARAM)
        Log.d("Worker", "My param is $param")

        // Sucess
        return Result.success()
        /*
        return Result.retry()
        return Result.failure()

         */
    }
}