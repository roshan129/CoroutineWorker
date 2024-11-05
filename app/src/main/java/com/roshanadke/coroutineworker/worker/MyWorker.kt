package com.roshanadke.coroutineworker.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.roshanadke.coroutineworker.data.MyRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltWorker
class MyWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParameters: WorkerParameters,
    private val myRepository: MyRepository
): CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {
        println("in here. ")
        println("result: ${myRepository.fetchData()}")
        return Result.success()
    }

}


fun scheduleFetchConfigWork(applicationContext: Context) {
    println("scheduleFetchConfigWork called")

    //workManager.enqueueUniquePeriodicWork(Constants.UPDATE_CACHE_WORKER, ExistingPeriodicWorkPolicy.UPDATE, request)

    val initialWorkRequest = OneTimeWorkRequestBuilder<MyWorker>().build()

    WorkManager.getInstance(applicationContext)
        .beginUniqueWork(
            "myWork",
            ExistingWorkPolicy.KEEP,
            initialWorkRequest,
        )
        .enqueue()

    println("scheduleFetchConfigWork called finish")
}