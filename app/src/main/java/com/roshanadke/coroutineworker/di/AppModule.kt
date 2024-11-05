package com.roshanadke.coroutineworker.di

import com.roshanadke.coroutineworker.data.MyRepository
import com.roshanadke.coroutineworker.data.MyRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /*@Binds
    @Singleton
    abstract fun bindMyRepository(myRepositoryImpl: MyRepositoryImpl): MyRepository*/

    @Provides
    @Singleton
    fun provideMyRepo(): MyRepository {
        return MyRepositoryImpl()
    }
}