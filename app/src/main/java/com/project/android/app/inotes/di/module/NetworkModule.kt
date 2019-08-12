package com.project.android.app.inotes.di.module

import com.project.android.app.inotes.data.remote.ApiService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideApiService() = ApiService

}