package com.roshanadke.coroutineworker

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.WorkManager
import com.roshanadke.coroutineworker.di.HiltWorkerFactoryEntryPoint
import com.roshanadke.coroutineworker.worker.scheduleFetchConfigWork
import dagger.hilt.EntryPoints
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asExecutor
import javax.inject.Inject

@HiltAndroidApp
class MyApp : Application(), Configuration.Provider {
/*
    @Inject
    lateinit var workerFactory: HiltWorkerFactory*/

    override fun onCreate() {
        super.onCreate()
        WorkManager.initialize(this, workManagerConfiguration)

        scheduleFetchConfigWork(applicationContext)
    }

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setExecutor(Dispatchers.Default.asExecutor())
            .setWorkerFactory(EntryPoints.get(this,
                HiltWorkerFactoryEntryPoint::class.java).workerFactory())
            //.setWorkerFactory(workerFactory)
            .setTaskExecutor(Dispatchers.Default.asExecutor())
            .setMinimumLoggingLevel(android.util.Log.DEBUG)
            .build()

 /*   override fun getWorkManagerConfiguration(): Configuration  =
        Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .setMinimumLoggingLevel(android.util.Log.DEBUG)
            .build()*/

}