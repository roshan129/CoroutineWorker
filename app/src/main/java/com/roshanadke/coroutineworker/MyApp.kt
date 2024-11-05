package com.roshanadke.coroutineworker

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.roshanadke.coroutineworker.worker.MyWorker
import com.roshanadke.coroutineworker.worker.scheduleFetchConfigWork
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MyApp : Application() {
    /*
    @Inject
    lateinit var workerFactory: HiltWorkerFactory
*/

    @Inject
    lateinit var workManager: WorkManager

    override fun onCreate() {
        super.onCreate()
        //WorkManager.initialize(this, workManagerConfiguration)


        //scheduleFetchConfigWork(workManager)

        //scheduleFetchConfigWork(applicationContext)
    }

    private fun scheduleFetchConfigWork(workManager: WorkManager) {
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

/*    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .setMinimumLoggingLevel(android.util.Log.DEBUG)
            .build()*/

 /*   override fun getWorkManagerConfiguration(): Configuration  =
        Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .setMinimumLoggingLevel(android.util.Log.DEBUG)
            .build()*/

}