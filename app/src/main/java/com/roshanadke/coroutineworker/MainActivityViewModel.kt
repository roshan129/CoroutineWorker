package com.roshanadke.coroutineworker

import androidx.lifecycle.ViewModel
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.roshanadke.coroutineworker.worker.MyWorker
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val workManager: WorkManager
) : ViewModel() {

 /*   init {
        scheduleFetchConfigWork(workManager)
    }
*/

    fun scheduleFetchConfigWork() {
        println("scheduleFetchConfigWork called first")

        //workManager.enqueueUniquePeriodicWork(Constants.UPDATE_CACHE_WORKER, ExistingPeriodicWorkPolicy.UPDATE, request)

        val initialWorkRequest = OneTimeWorkRequestBuilder<MyWorker>().build()

        workManager.beginUniqueWork(
            "myWork",
            ExistingWorkPolicy.KEEP,
            initialWorkRequest,
        )
            .enqueue()

        println("scheduleFetchConfigWork called finish")
    }
}