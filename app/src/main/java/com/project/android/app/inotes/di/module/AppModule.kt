package com.project.android.app.inotes.di.module

import com.project.android.app.inotes.data.remote.ApiService
import com.project.android.app.inotes.data.repository.INotesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
    includes = [
        NetworkModule::class
    ]
)
class AppModule {

    @Singleton
    @Provides
    fun provideINotesRepository(apiService: ApiService) = INotesRepository(apiService.createINotesService())

}