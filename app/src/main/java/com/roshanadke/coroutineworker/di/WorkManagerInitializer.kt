package com.roshanadke.coroutineworker.di

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorkerFactory
import androidx.startup.Initializer
import androidx.work.Configuration
import androidx.work.WorkManager
import dagger.Module
import dagger.Provides
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asExecutor
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object WorkManagerInitializer: Initializer<WorkManager> {
    @Provides
    @Singleton
    override fun create(@ApplicationContext context: Context): WorkManager {
        val entryPoint = EntryPointAccessors.fromApplication(
            context,
            WorkManagerInitializerEntryPoint::class.java
        )
        val configuration = Configuration.Builder()
            .setExecutor(Dispatchers.Default.asExecutor())
            .setWorkerFactory(entryPoint.hiltWorkerFactory())
            .setTaskExecutor(Dispatchers.Default.asExecutor())
            .setMinimumLoggingLevel(Log.DEBUG)
            .build()

        WorkManager.initialize(context, configuration)
        return WorkManager.getInstance(context)
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }

    @InstallIn(SingletonComponent::class)
    @EntryPoint
    interface WorkManagerInitializerEntryPoint {
        fun hiltWorkerFactory(): HiltWorkerFactory
    }
}

/*
@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val workManager: WorkManager,
): ViewModel() {

}

*/
